package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.blog.configurations.FlexmarkConfig;
import org.tanglizi.blog.entities.Article;
import org.tanglizi.blog.services.ArticleService;

import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public String arcticlePage(@PathVariable("id") Integer articleId, Map map){
        Article article = articleService.findArticleById(articleId);
        long articleCount = articleService.findArticleCount();

        map.put("article", article);
        map.put("articleCount", articleCount);
        map.put("markdownContent", FlexmarkConfig.FlexmarkParser.parse(article.getContent()));

        return "article";
    }
}
