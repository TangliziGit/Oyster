package org.tanglizi.blog.services;

import org.springframework.data.domain.Page;
import org.tanglizi.blog.entities.Comment;

public interface CommentService {
    Page<Comment> findCommentsByArticleId(Integer articleId, int pageNumber, int limit);
    Comment findCommentById(Integer id);
    Comment insertComment(Comment comment);
    long findCommentCount();
}
