package org.tanglizi.oyster.front.services.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tanglizi.oyster.common.dao.repositories.CommentRepository;
import org.tanglizi.oyster.common.entities.Comment;
import org.tanglizi.oyster.front.services.CommentService;

import javax.annotation.Resource;
import java.util.List;

@Service("FrontCommentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentsBtArticleId(Integer articleId, int pageNum, int limit) {
        return commentRepository.findCommentsByArticleId(articleId,
                PageRequest.of(pageNum, limit, Sort.by(Sort.Direction.DESC, "createTimestamp"))).getContent();
    }
}
