package org.tanglizi.oyster.api.services;

import org.springframework.data.domain.Page;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.common.entities.Comment;

import java.util.List;

public interface CommentService {
    RESTfulResponse<List<Comment>> getCommentsByArticleId(Integer articleId, int pageNumber, int limit);
    RESTfulResponse insertComment(Comment comment);
}
