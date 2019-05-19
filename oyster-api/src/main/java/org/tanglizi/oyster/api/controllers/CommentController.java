package org.tanglizi.oyster.api.controllers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.api.configurations.OysterApiConfig;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.utils.GlobalCacheKit;
import org.tanglizi.oyster.common.entities.Comment;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CommentService;
import org.tanglizi.oyster.common.utils.IPKit;
import org.tanglizi.oyster.common.utils.SecurityKit;
import org.tanglizi.oyster.common.utils.StringKit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/v1/comments")
public class CommentController {
    private Logger logger= LoggerFactory.getLogger(CommentController.class);

    @Resource(name = "ApiCommentService")
    private CommentService commentService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<RESTfulResponse<List<Comment>>>
        getComments(@RequestParam("articleId") Integer articleId,
                    @RequestParam(value = "page", defaultValue = "0") int page,
                    @RequestParam(value = "limit", defaultValue = "20") int limit){

        List<Comment> comments = commentService.getCommentsByArticleId(articleId, page, limit);
        if (comments==null || comments.size()==0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("no category exists"));

        RESTfulResponse<List<Comment>> response=RESTfulResponse.ok();
        response.setData(comments);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RESTfulResponse> makeComment(
            Comment comment, HttpServletRequest request,
            @RequestParam("_csrf_token") String csrfToken){
        logger.info(comment.toString());
        logger.info("csrfToken: "+csrfToken);
        String referer=request.getHeader("Referer");
        RESTfulResponse response=null;
        GlobalCacheKit globalCache= GlobalCacheKit.getCacheSingleton();

        // 这里应该匹配一下HOST
        if (null == response && StringUtils.isBlank(referer)) {
            response = RESTfulResponse.fail();
            logger.info("Blocked by referer.");
        }

        if (null == response && OysterCommonConfig.CRSF_TOKEN.equals(globalCache.get(csrfToken))) {
            response = RESTfulResponse.fail();
            logger.info("Blocked by csrf_token.");
        }

        String ipWithAritleId = IPKit.getIPAddress(request)+":"+comment.getArticleId();
        Object lastPostTime = globalCache.get(ipWithAritleId);
        if (null == response && null != lastPostTime) {
            Long interval = System.currentTimeMillis() / 1000 - (Long) lastPostTime / 1000;
            if (interval < OysterApiConfig.COMMENT_POST_INTERVAL)
                response = RESTfulResponse.fail("You comment this article too frequently.");
        }

        if (null == response && StringUtils.isBlank(comment.getContent()))
            response=RESTfulResponse.fail("The comment can not be empty.");

        if (null == response && comment.getContent().length() > OysterApiConfig.COMMENT_LENGTH)
            response=RESTfulResponse.fail("The comment can not be too large.");

        if (null == response && !StringKit.isEmail(comment.getUserEmail()))
            response=RESTfulResponse.fail("Please enter a correct email.");

        SecurityKit.cleanXSS(comment.getContent());
        SecurityKit.cleanXSS(comment.getUserName());
        SecurityKit.cleanXSS(comment.getUserEmail());

        if (null != response) {
            logger.info("Error response: "+response.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        String failMessage=commentService.insertComment(comment);

        if (null != failMessage)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(RESTfulResponse.fail(failMessage));

        globalCache.set(ipWithAritleId, System.currentTimeMillis());
        return ResponseEntity.ok(RESTfulResponse.ok());
    }
}
