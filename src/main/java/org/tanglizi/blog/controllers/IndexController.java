package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.blog.entities.Article;
import org.tanglizi.blog.services.ArticleService;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @GetMapping({"/index", "/home", "/"})
    public String index(@RequestParam(name = "page", defaultValue = "0") Integer page,
                        @RequestParam(name = "limit", defaultValue = "20") Integer limit, Map map){
        Page<Article> articlePage=articleService.findArticles(page, limit);

        map.put("articles", articlePage.getContent());
        map.put("page", page);
        map.put("limit", limit);
        map.put("pageCount", articlePage.getTotalPages());

        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/archives")
    public String archives(@RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "limit", defaultValue = "20") Integer limit, Map map){
        Page<Article> articlePage=articleService.findArticles(page, limit);

        map.put("articles", articlePage.getContent());
        map.put("page", page);
        map.put("limit", limit);
        map.put("pageCount", articlePage.getTotalPages());

        return "archives";
    }
}
