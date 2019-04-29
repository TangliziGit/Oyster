package org.tanglizi.blog.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tanglizi.blog.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select a.article_id from article a, category_relation cr where cr.article_id=a.article_id and cr.category_id=?1",
        nativeQuery = true)
    List<Integer> findArticleIdsById(Integer categoryId);
}
