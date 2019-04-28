package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.blog.entities.Comment;
import org.tanglizi.blog.services.CommentService;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    @ResponseBody
    public List<Comment> commentsPage(@RequestParam("articleId") Integer articleId,
                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "20") int limit){
        return commentService
                .findCommentsByArticleId(articleId, page, limit)
                .getContent();
    }

    @PostMapping
    @ResponseBody
    public Comment makeComment(Comment comment){
        return commentService.insertComment(comment);
    }
}
