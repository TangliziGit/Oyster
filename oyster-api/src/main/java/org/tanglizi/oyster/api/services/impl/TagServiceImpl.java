package org.tanglizi.oyster.api.services.impl;

import org.springframework.stereotype.Service;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.TagService;
import org.tanglizi.oyster.common.dao.repositories.TagRepository;
import org.tanglizi.oyster.common.model.TagModel;
import org.tanglizi.oyster.common.entities.Tag;

import javax.annotation.Resource;
import java.util.List;

@Service("ApiTagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagRepository tagRepository;

    @Override
    public RESTfulResponse<List<Tag>> getAllTagsResponse() {
        List<Tag> tags=tagRepository.findAll();
        if (tags==null || tags.size()==0)
            return RESTfulResponse.fail("no category exists");

        RESTfulResponse<List<Tag>> response=RESTfulResponse.ok();
        response.setPayload(tags);
        return response;
    }

    @Override
    public RESTfulResponse<TagModel> getTagModelResponse(Integer tagId) {
        Tag tag=tagRepository.findById(tagId).orElse(null);
        if (tag==null)
            return RESTfulResponse.fail("could not find the tag");

        TagModel tagModel=new TagModel(){{
            setId(tagId);
            setName(tag.getName());
            setArticlesId(tagRepository.findArticleIdsByTagId(tagId));
        }};

        RESTfulResponse<TagModel> response=RESTfulResponse.ok();
        response.setPayload(tagModel);

        return response;
    }

}
