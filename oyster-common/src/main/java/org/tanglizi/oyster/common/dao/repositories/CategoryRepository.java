package org.tanglizi.oyster.common.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.tanglizi.oyster.common.entities.Category;

import java.util.List;

@Component
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select article_id from category_relation where category_id=?1", nativeQuery = true)
    List<Integer> findArticleIdsByCategoryId(Integer categoryId);
}
