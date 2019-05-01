package org.tanglizi.blog.services;

import org.tanglizi.blog.dto.entities.Article;

import java.util.List;

public interface SearchService {
    List<Article> findArticleByTitleLikeListAndContentLikeList(
            List<String> titleLikeList, List<String> contentLikeList);
}
