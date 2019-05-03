package org.tanglizi.oyster.api.services;

import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.common.model.TagModel;
import org.tanglizi.oyster.common.entities.Tag;

import java.util.List;

public interface TagService {
    RESTfulResponse<List<Tag>> getAllTagsResponse();
    RESTfulResponse<TagModel> getTagModelResponse(Integer categoryId);
}
