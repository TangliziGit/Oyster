package org.tanglizi.blog.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tanglizi.blog.dto.entities.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "select article_id from tag_relation where tag_id=?1", nativeQuery = true)
    List<Integer> findArticleIdsById(Integer tagId);
}
