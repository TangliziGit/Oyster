package org.tanglizi.oyster.services;

import org.springframework.data.domain.Page;
import org.tanglizi.oyster.entities.Article;

import java.util.List;

public interface ArticleService {
    Page<Article> findArticles(int pageNumber, int limit);
    Article findArticleById(Integer id);
    long findArticleCount();
    Integer findNextArticleId(Integer articleId);
    Integer findPrevArticleId(Integer articleId);
    Page<Article> findArticlesByLikeList(List<String> titleLkeList, List<String> contentLikeList,
                                         int pageNumber, int limit);
}
