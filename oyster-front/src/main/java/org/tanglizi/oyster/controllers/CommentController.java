package org.tanglizi.oyster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.dto.RESTfulResponse;
import org.tanglizi.oyster.dto.entities.Comment;
import org.tanglizi.oyster.services.CommentService;

import java.util.Date;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // it should be writed in api module.
    // @GetMapping
    // @ResponseBody
    // public List<Comment> commentsPage(@RequestParam("articleId") Integer articleId,
    //                                   @RequestParam(value = "page", defaultValue = "0") int page,
    //                                   @RequestParam(value = "limit", defaultValue = "20") int limit){
    //     return commentService
    //             .findCommentsByArticleId(articleId, page, limit)
    //             .getContent();
    // }

    @PostMapping
    @ResponseBody
    public RESTfulResponse makeComment(Comment comment){
        comment.setCreateTimestamp(new Date());
        /*
        * TODO: 各种判断
        * */

        commentService.insertComment(comment);
        return RESTfulResponse.ok();
    }
}
