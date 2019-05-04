package org.tanglizi.oyster.api.services;

import org.tanglizi.oyster.common.model.TagModel;
import org.tanglizi.oyster.common.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    TagModel getTagModel(Integer categoryId);
}
