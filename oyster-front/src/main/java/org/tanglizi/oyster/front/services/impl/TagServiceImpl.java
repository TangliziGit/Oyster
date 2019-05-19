package org.tanglizi.oyster.front.services.impl;

import org.springframework.stereotype.Service;
import org.tanglizi.oyster.common.dao.repositories.TagRepository;
import org.tanglizi.oyster.common.entities.Tag;
import org.tanglizi.oyster.front.services.TagService;

import javax.annotation.Resource;
import java.util.List;

@Service("FrontTagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagRepository tagRepository;

    @Override
    public List<Tag> findTagsByArticleId(Integer articleId) {
        return tagRepository.findTagsByArticleId(articleId);
    }
}
