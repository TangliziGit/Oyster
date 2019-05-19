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
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public TagModel getTagModel(Integer tagId) {
        Tag tag=tagRepository.findById(tagId).orElse(null);
        if (tag==null)
            return null;

        TagModel tagModel=new TagModel(){{
            setId(tagId);
            setName(tag.getName());
            setArticlesId(tagRepository.findArticleIdsByTagId(tagId));
        }};

        return tagModel;
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Integer tagId) {
        tagRepository.deleteById(tagId);
    }
}
