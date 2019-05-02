package org.tanglizi.oyster.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tanglizi.oyster.dto.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findCommentsByArticleId(Integer articleId, Pageable pageable);
}
