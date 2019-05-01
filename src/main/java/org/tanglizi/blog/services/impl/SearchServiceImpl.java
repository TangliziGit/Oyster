package org.tanglizi.blog.services.impl;

import org.tanglizi.blog.dto.entities.Article;
import org.tanglizi.blog.services.SearchService;

import java.util.List;

public class SearchServiceImpl implements SearchService {
    @Override
    public List<Article> findArticleByTitleLikeListAndContentLikeList(
            List<String> titleLikeList, List<String> contentLikeList) {
        return null;
    }
}
