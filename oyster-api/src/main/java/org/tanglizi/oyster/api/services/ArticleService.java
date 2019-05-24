package org.tanglizi.oyster.api.services;

import org.springframework.data.domain.Page;
import org.tanglizi.oyster.common.entities.Article;

import java.util.List;

public interface ArticleService {
    Page<Article> getArticlePage(int pageNumber, int limit);
    Article getArticle(Integer articleId);
    void saveArticle(Article article);
    String updateArticleReturnErrorMessage(Article article);
    void deleteArticle(Integer articleId);
}
