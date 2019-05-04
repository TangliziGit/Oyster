package org.tanglizi.oyster.common.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.tanglizi.oyster.common.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
    @Query(value = "select min(article_id) from article where article_id > ?1 group by article_id", nativeQuery = true)
    Integer findNextArticleId(Integer articleId);
    @Query(value = "select max(article_id) from article where article_id < ?1 group by article_id", nativeQuery = true)
    Integer findPrevArticleId(Integer articleId);
}
