package org.tanglizi.blog.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tanglizi.blog.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
