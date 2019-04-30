package org.tanglizi.blog.services.impl;

import org.springframework.stereotype.Service;
import org.tanglizi.blog.dao.repositories.TagRepository;
import org.tanglizi.blog.dto.TagInfo;
import org.tanglizi.blog.dto.entities.Tag;
import org.tanglizi.blog.services.TagService;

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
}
