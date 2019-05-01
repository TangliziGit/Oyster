package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.blog.configurations.BlogConfig;
import org.tanglizi.blog.dto.entities.Article;
import org.tanglizi.blog.services.ArticleService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ArticleService articleService;

    // you can pass am array with url like:
    //  /search?title=xxx&title=xxxx
    @PostMapping
    public String searchArticle(// @RequestParam(value = "titleLike", defaultValue = "") List<String> titleLikeList,
                                // @RequestParam(value = "contentLike", defaultValue = "") List<String> contentLikeList,
                                @RequestParam(value = "titleLike", defaultValue = "") String titleLike,
                                @RequestParam(value = "contentLike", defaultValue = "") String contentLike,
                                @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                @RequestParam(value = "limit", defaultValue = "20") int limit,
                                Map map){
        List<String> titleLikeList= Arrays.asList(titleLike.split(","));
        List<String> contentLikeList=Arrays.asList(contentLike.split(","));
        Page<Article> articlePage=
                articleService.findArticlesByLikeList(titleLikeList, contentLikeList, pageNumber, limit);

        map.put("articles", articlePage.getContent());
        map.put("page", pageNumber);
        map.put("limit", limit);
        map.put("pageCount", articlePage.getTotalPages());

        return BlogConfig.THEME_PATH+"search";
    }

    @GetMapping
    public String search(Map map){
        map.put("page", 0);
        map.put("limit", 20);
        map.put("pageCount", 0);

        return BlogConfig.THEME_PATH+"search";
    }
}

