package org.tanglizi.blog.services;

import org.springframework.data.domain.Page;
import org.tanglizi.blog.dto.entities.Article;

public interface ArticleService {
    Page<Article> findArticles(int pageNumber, int limit);
    Article findArticleById(Integer id);
    long findArticleCount();
    Integer findNextArticleId(Integer articleId);
    Integer findPrevArticleId(Integer articleId);
}
