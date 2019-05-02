package org.tanglizi.oyster.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tanglizi.oyster.dto.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select article_id from category_relation where category_id=?1", nativeQuery = true)
    List<Integer> findArticleIdsById(Integer categoryId);
}
