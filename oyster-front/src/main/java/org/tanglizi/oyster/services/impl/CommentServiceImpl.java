package org.tanglizi.oyster.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tanglizi.oyster.dao.repositories.CommentRepository;
import org.tanglizi.oyster.dto.entities.Comment;
import org.tanglizi.oyster.services.CommentService;

import javax.annotation.Resource;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> findCommentPageByArticleId(Integer articleId, int pageNumber, int limit) {
        Page<Comment> commentPage = commentRepository.findCommentsByArticleId(articleId,
                PageRequest.of(pageNumber, limit, Sort.by("createTimestamp")));
        return commentPage;
    }

    @Override
    public Comment findCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment insertComment(Comment comment){
        return commentRepository.save(comment);
    }

    @Override
    public long findCommentCount() {
        return commentRepository.count();
    }
}
