package org.tanglizi.oyster.api.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.common.dao.repositories.ArticleRepository;
import org.tanglizi.oyster.common.entities.Article;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Service("ApiArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> getArticlePage(int pageNumber, int limit) {
        return articleRepository.findAll(
                PageRequest.of(pageNumber, limit, Sort.by(Sort.Direction.DESC, "createTimpestamp"))
        );
    }

    @Override
    public Article getArticle(Integer articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }

    @Override
    @Transactional
    public void saveArticle(Article article) {
        article.setArticleId(null);
        article.setCreateTimestamp(new Date());
        article.setLastModifiedTimestamp(new Date());
        article.setHit(0);

        articleRepository.save(article);
    }

    @Override
    public String updateArticleReturnErrorMessage(Article article) {
        Article articleOld;

        if (null == article.getArticleId()
                || (articleOld=articleRepository.findById(article.getArticleId()).orElse(null)) == null)
            return "The article not exists";

        article.setLastModifiedTimestamp(new Date());
        article.setHit(articleOld.getHit());
        article.setCreateTimestamp(articleOld.getCreateTimestamp());

        articleRepository.save(article);
        return null;
    }

    @Override
    public void deleteArticle(Integer articleId) {
        articleRepository.deleteById(articleId);
    }
}
