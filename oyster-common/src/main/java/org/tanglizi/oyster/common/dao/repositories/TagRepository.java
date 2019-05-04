package org.tanglizi.oyster.common.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.tanglizi.oyster.common.entities.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "select article_id from tag_relation where tag_id=?1", nativeQuery = true)
    List<Integer> findArticleIdsByTagId(Integer tagId);

    @Query(value = "select t.* from tag_relation tr, tag t where tr.article_id=?1 and tr.tag_id = t.tag_id",
        nativeQuery = true)
    List<Tag> findTagsByArticleId(Integer articleId);
}
