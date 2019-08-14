package org.tanglizi.oyster.back.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.back.configurations.OysterBackConfig;
import org.tanglizi.oyster.common.entities.Article;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/back")
public class IndexController {

    @Resource(name = "ApiArticleService")
    private ArticleService articleService;

    @GetMapping({"/index", "/home", "/"})
    public String index(@RequestParam(name = "page", defaultValue = "0") Integer page,
                        @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                        Map<String, Object> map){
        Page<Article> articlePage=articleService.getArticlePage(page, limit);

        map.put("articles", articlePage.getContent());
        map.put("page", page);
        map.put("limit", limit);
        map.put("pageCount", articlePage.getTotalPages());

        return OysterBackConfig.themePath+"index";
    }

    @GetMapping("/about")
    public String about(){
        return OysterBackConfig.themePath+"about";
    }

}
