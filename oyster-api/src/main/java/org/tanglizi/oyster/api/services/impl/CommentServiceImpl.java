package org.tanglizi.oyster.api.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CommentService;
import org.tanglizi.oyster.common.dao.repositories.ArticleRepository;
import org.tanglizi.oyster.common.dao.repositories.CommentRepository;
import org.tanglizi.oyster.common.entities.Comment;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("ApiCommentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private ArticleRepository articleRepository;

    @Override
    public List<Comment> getCommentsByArticleId(Integer articleId, int pageNumber, int limit) {
        return commentRepository.findCommentsByArticleId(articleId,
                PageRequest.of(pageNumber, limit, Sort.by(Sort.Direction.DESC, "createTimestamp"))
        ).getContent();
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public void deleteCommentById(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment getCommentByArticleIdAndNumber(Integer articleId, Integer number) {
        List<Comment> comments = commentRepository.findCommentsByArticleId(articleId,
                PageRequest.of(number, 1, Sort.by(Sort.Direction.DESC, "createTimestamp"))
        ).getContent();

        if (comments.size() == 0)
            return null;
        return comments.get(0);
    }

    @Override
    public List<Comment> getAllComments(int pageNumber, int limit) {
        return commentRepository.findAll(
                PageRequest.of(pageNumber, limit, Sort.by(Sort.Direction.DESC, "createTimestamp"))
        ).getContent();
    }

    @Override
    public String saveCommentReturnErrorMessage(Comment comment){
        comment.setCreateTimestamp(new Date());

        if (null == comment.getArticleId() || null == articleRepository.findById(comment.getArticleId()))
            return "Please post a comment to a article existing.";

        // 作为数据库的默认值，id为自增主键，timestamp为默认当前时间
        comment.setCommentId(null);
        comment.setCreateTimestamp(new Date());

        commentRepository.save(comment);
        return null;
    }

}
