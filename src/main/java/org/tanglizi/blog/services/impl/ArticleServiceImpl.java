package org.tanglizi.blog.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tanglizi.blog.entities.Article;
import org.tanglizi.blog.dao.repositories.ArticleRepository;
import org.tanglizi.blog.services.ArticleService;

import javax.annotation.Resource;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> findArticles(int pageNumber, int limit) {
        Page<Article> articlePage=articleRepository.findAll(
                PageRequest.of(pageNumber, limit, Sort.by("createTimestamp")));
        return articlePage;
    }

    @Override
    public Article findArticleById(Integer id) {
        // ?
        return articleRepository.findById(id).get();
    }

    @Override
    public long findArticleCount() {
        return articleRepository.count();
    }
}
