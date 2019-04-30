package org.tanglizi.blog.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tanglizi.blog.dao.repositories.CommentRepository;
import org.tanglizi.blog.dto.entities.Comment;
import org.tanglizi.blog.services.CommentService;

import javax.annotation.Resource;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> findCommentsByArticleId(Integer articleId, int pageNumber, int limit) {
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
