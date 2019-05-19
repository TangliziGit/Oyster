package org.tanglizi.oyster.front.services;

import org.tanglizi.oyster.common.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findTagsByArticleId(Integer articleId);
}
