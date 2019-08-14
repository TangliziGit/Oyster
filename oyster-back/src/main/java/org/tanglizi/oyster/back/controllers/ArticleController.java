package org.tanglizi.oyster.back.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.back.configurations.OysterBackConfig;
import org.tanglizi.oyster.common.entities.Article;
import org.thymeleaf.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/back/articles")
public class ArticleController {

    @Resource(name = "ApiArticleService")
    private ArticleService articleService;

    @GetMapping("/{id}")
    public String articleEditPage(@PathVariable("id") Integer id,
                                  Map<String, Object> map){
        Article article=articleService.getArticle(id);

        if (null == article) return "error_404";

        map.put("article", article);

        return OysterBackConfig.themePath+"edit_article";
    }
}
