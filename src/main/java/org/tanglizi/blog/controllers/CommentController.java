package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.blog.dto.RESTfulResponse;
import org.tanglizi.blog.dto.entities.Comment;
import org.tanglizi.blog.services.CommentService;

import java.util.Date;
import java.util.List;

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

        return RESTfulResponse.ok();
    }
}
