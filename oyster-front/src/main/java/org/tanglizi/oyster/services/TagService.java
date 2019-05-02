package org.tanglizi.oyster.services;

import org.tanglizi.oyster.dto.TagInfo;
import org.tanglizi.oyster.dto.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();
    TagInfo getTagInfo(Integer tagId);
    List<Tag> findTagsByArticleId(Integer articleId);
}
