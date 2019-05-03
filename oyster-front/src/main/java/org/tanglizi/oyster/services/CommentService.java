package org.tanglizi.oyster.services;

import org.springframework.data.domain.Page;
import org.tanglizi.oyster.entities.Comment;

public interface CommentService {
    Page<Comment> findCommentPageByArticleId(Integer articleId, int pageNumber, int limit);
    Comment findCommentById(Integer id);
    Comment insertComment(Comment comment);
    long findCommentCount();
}
