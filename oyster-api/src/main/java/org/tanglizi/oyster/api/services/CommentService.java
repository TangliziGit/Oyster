package org.tanglizi.oyster.api.services;

import org.tanglizi.oyster.common.entities.Comment;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByArticleId(Integer articleId, int pageNumber, int limit);
    List<Comment> getAllComments(int pageNumber, int limit);
    String saveCommentReturnErrorMessage(Comment comment);
    Comment getCommentById(Integer commentId);
    void deleteCommentById(Integer commentId);
    Comment getCommentByArticleIdAndNumber(Integer articleId, Integer number);
}
