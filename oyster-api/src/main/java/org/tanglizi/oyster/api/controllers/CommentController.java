package org.tanglizi.oyster.api.controllers;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.AbstractReferenceTypeDelegate;
import org.hibernate.annotations.GeneratorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.api.configurations.OysterApiConfig;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.utils.GlobalCacheKit;
import org.tanglizi.oyster.common.entities.Comment;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CommentService;
import org.tanglizi.oyster.common.utils.IPKit;
import org.tanglizi.oyster.common.utils.SecurityKit;
import org.tanglizi.oyster.common.utils.StringKit;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.smartcardio.CardTerminal;
import java.security.Security;
import java.util.List;

/*
* TODO:
*  comments                 get     done
*  comments/1               get     done
*  comments/1               delete  done
*  articles/1/comments      get     done
*  articles/1/comments      post    done
*  articles/1/comments/1    get     done
*
* */

@Controller
@RequestMapping("/api/v1")
public class CommentController {
    private Logger logger= LoggerFactory.getLogger(CommentController.class);

    @Resource(name = "ApiCommentService")
    private CommentService commentService;

    @Resource(name = "ApiArticleService")
    private ArticleService articleService;

    @GetMapping("/comments")
    @ResponseBody
    public ResponseEntity<RESTfulResponse<List<Comment>>> getAllComment(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit){


        List<Comment> comments = commentService.getAllComments(page, limit);
        if (comments==null || comments.size()==0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("no comments exists"));

        RESTfulResponse<List<Comment>> response=RESTfulResponse.ok();
        response.setData(comments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/comments/{commentId}")
    @ResponseBody
    public ResponseEntity<RESTfulResponse<Comment>> getComment(
            @PathVariable("commentId") Integer commentId){
        Comment comment=commentService.getCommentById(commentId);

        if (null == comment)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("no such comment"));

        RESTfulResponse<Comment> response=RESTfulResponse.ok();
        response.setData(comment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseBody
    public ResponseEntity<RESTfulResponse> deleteComment(
            HttpServletRequest request,
            @PathVariable("commentId") Integer commentId,
            @RequestParam("_csrf_token") String csrfToken){

        logger.info("csrfToken: "+csrfToken);
        RESTfulResponse response=null;
        SecurityKit.SecurityBlockType securityBlockType=SecurityKit.securityBlock(request, csrfToken);

        if (null != securityBlockType)
            response=RESTfulResponse.fail();

        if (null != response)
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(response);

        commentService.deleteCommentById(commentId);
        return ResponseEntity.ok(RESTfulResponse.ok());
    }

    @GetMapping("/articles/{articleId}/comments")
    @ResponseBody
    public ResponseEntity<RESTfulResponse<List<Comment>>>
        getCommentsByArticleId(@PathVariable("articleId") Integer articleId,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "limit", defaultValue = "20") int limit){

        List<Comment> comments = commentService.getCommentsByArticleId(articleId, page, limit);
        if (comments==null || comments.size()==0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("no comments exists"));

        RESTfulResponse<List<Comment>> response=RESTfulResponse.ok();
        response.setData(comments);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/articles/{articleId}/comments/{commentId}")
    public ResponseEntity<RESTfulResponse<Comment>> getCommentByArticleIdAndNumber(
            @PathVariable("articleId") Integer articleId,
            @PathVariable("commentId") Integer commentId){

        Comment comment=commentService.getCommentByArticleIdAndNumber(articleId, commentId);

        if (null == comment)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("no such comment"));

        RESTfulResponse<Comment> response=RESTfulResponse.ok();
        response.setData(comment);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/articles/{articleId}/comments")
    @ResponseBody
    public ResponseEntity<RESTfulResponse> makeComment(
                @PathVariable("articleId") Integer articleId,
                Comment comment, HttpServletRequest request,
                @RequestParam("_csrf_token") String csrfToken){
        logger.info(comment.toString());
        logger.info("csrfToken: "+csrfToken);
        RESTfulResponse response=null;
        GlobalCacheKit globalCache=GlobalCacheKit.getCacheSingleton();
        SecurityKit.SecurityBlockType securityBlockType=SecurityKit.securityBlock(request, csrfToken);

        if (null != securityBlockType)
            response=RESTfulResponse.fail();

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
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        comment.setArticleId(articleId);
        String failMessage=commentService.saveCommentReturnErrorMessage(comment);

        if (null != failMessage)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(RESTfulResponse.fail(failMessage));

        globalCache.set(ipWithAritleId, System.currentTimeMillis());
        return ResponseEntity.ok(RESTfulResponse.ok());
    }
}
