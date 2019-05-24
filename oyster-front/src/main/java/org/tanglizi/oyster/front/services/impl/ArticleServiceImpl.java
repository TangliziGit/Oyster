package org.tanglizi.oyster.front.services.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tanglizi.oyster.front.services.ArticleService;
import org.tanglizi.oyster.common.dao.queries.ArticleQuery;
import org.tanglizi.oyster.common.entities.Article;
import org.tanglizi.oyster.common.dao.repositories.ArticleRepository;

import javax.annotation.Resource;
import java.util.List;

@Service("FrontArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> findArticles(int pageNumber, int limit) {
        return articleRepository.findAll(
                PageRequest.of(pageNumber, limit, Sort.by("createTimestamp")));
    }

    @Override
    public Article findArticleById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public long findArticleCount() {
        return articleRepository.count();
    }

    @Override
    public Integer findNextArticleId(Integer articleId) {
        return articleRepository.findNextArticleId(articleId);
    }

    @Override
    public Integer findPrevArticleId(Integer articleId) {
        return articleRepository.findPrevArticleId(articleId);
    }

    @Override
    public Page<Article> findArticlesByLikeList(List<String> titleLkeList, List<String> contentLikeList,
                                                int pageNumber, int limit) {
        return articleRepository.findAll(new ArticleQuery(){{
            setCombineLogicType(LogicType.OR);
            setTitleLikeList(titleLkeList);
            setContentLikeList(contentLikeList);
        }}.toSpec(), PageRequest.of(pageNumber, limit, Sort.by("createTimestamp")));
    }
}
