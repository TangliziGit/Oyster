package org.tanglizi.oyster.api.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CommentService;
import org.tanglizi.oyster.common.dao.repositories.CommentRepository;
import org.tanglizi.oyster.common.entities.Comment;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("ApiCommentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByArticleId(Integer articleId, int pageNumber, int limit) {
        return commentRepository.findCommentsByArticleId(articleId,
                PageRequest.of(pageNumber, limit, Sort.by("createTimestamp"))).getContent();
    }

    @Override
    public String insertComment(Comment comment){
        comment.setCreateTimestamp(new Date());
        /*
         * TODO: 各种判断返回错误原因
         * */

        commentRepository.save(comment);
        return null;
    }

}
