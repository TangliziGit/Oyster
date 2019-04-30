package org.tanglizi.blog.services;

import org.tanglizi.blog.dto.TagInfo;
import org.tanglizi.blog.dto.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();
    TagInfo getTagInfo(Integer tagId);
}
