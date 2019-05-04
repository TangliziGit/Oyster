package org.tanglizi.oyster.api.services;

import org.tanglizi.oyster.common.entities.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByArticleId(Integer articleId, int pageNumber, int limit);
    String insertComment(Comment comment);
}
