package org.tanglizi.oyster.back.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.back.configurations.OysterBackConfig;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.entities.Article;
import org.tanglizi.oyster.common.utils.GlobalCacheKit;
import org.tanglizi.oyster.common.utils.SecurityKit;
import org.thymeleaf.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

@Controller(value = "BackArticleController")
@RequestMapping("/back/articles")
public class ArticleController {

    @Resource(name = "ApiArticleService")
    private ArticleService articleService;

    @GetMapping("/{id}")
    public String articleEditPage(@PathVariable("id") Integer id,
                                  Map<String, Object> map){
        Article article=articleService.getArticle(id);

        if (null == article) return "error_404";

        String csrfToken= SecurityKit.getCsrfToken();
        GlobalCacheKit.getCacheSingleton().set(csrfToken, OysterCommonConfig.CRSF_TOKEN);
        map.put("_csrf_token", csrfToken);
        map.put("article", article);

        return OysterBackConfig.themePath+"edit_article";
    }

    @GetMapping
    public String articles(@RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                           Map<String, Object> map){
        Page<Article> articlePage=articleService.getArticlePage(page, limit);

        map.put("articles", articlePage.getContent());
        map.put("page", page);
        map.put("limit", limit);
        map.put("pageCount", articlePage.getTotalPages());

        return OysterBackConfig.themePath+"index";
    }
}
