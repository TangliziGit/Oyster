package org.tanglizi.oyster.common.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.tanglizi.oyster.common.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findCommentsByArticleId(Integer articleId, Pageable pageable);
}
