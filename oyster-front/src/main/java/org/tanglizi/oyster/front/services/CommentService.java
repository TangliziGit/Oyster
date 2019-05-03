package org.tanglizi.oyster.front.services;

import org.tanglizi.oyster.common.entities.Comment;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentService {
    List<Comment> findCommentsBtArticleId(Integer articleId, int pageNum, int limit);
}
