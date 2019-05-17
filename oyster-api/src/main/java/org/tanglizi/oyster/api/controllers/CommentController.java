package org.tanglizi.oyster.api.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.common.entities.Comment;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CommentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/v1/comments")
public class CommentController {

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

    // 1. referer
    // 2. csrf
    // 3. xss
    // 4. email
    // 5. ip timer
    // 6. emoji
    @PostMapping
    @ResponseBody
    public ResponseEntity<RESTfulResponse> makeComment(
            Comment comment, HttpServletRequest request,
            @RequestParam("_crsf") String _crsf){
        String referer=request.getHeader("Referer");
        RESTfulResponse response=null;

        if (StringUtils.isBlank(referer))
            response=RESTfulResponse.fail();

        if ()

        String failMessage=commentService.insertComment(comment);

        if (failMessage!=null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(RESTfulResponse.fail(failMessage));

        return ResponseEntity.ok(RESTfulResponse.ok());
    }
}
