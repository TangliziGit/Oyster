package org.tanglizi.blog.services;

import org.springframework.data.domain.Page;
import org.tanglizi.blog.entities.Article;

import java.util.Optional;

public interface ArticleService {
    Page<Article> findArticles(int pageNumber, int limit);
    Article findArticleById(Integer id);
    long findArticleCount();
}
