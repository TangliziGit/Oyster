package org.tanglizi.oyster.services.impl;

import org.springframework.stereotype.Service;
import org.tanglizi.oyster.dao.repositories.TagRepository;
import org.tanglizi.oyster.model.TagInfo;
import org.tanglizi.oyster.entities.Tag;
import org.tanglizi.oyster.services.TagService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public TagInfo getTagInfo(Integer tagId) {
        Tag tag=tagRepository.findById(tagId).orElse(null);
        if (tag==null) return null;

        return new TagInfo(){{
            setId(tagId);
            setName(tag.getName());
            setArticlesId(
                    tagRepository.findArticleIdsById(tagId)
            );
        }};
    }

    @Override
    public List<Tag> findTagsByArticleId(Integer articleId) {
        return tagRepository.findTagsByArticleId(articleId);
    }
}
