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

@Controller("BackIndexController")
@RequestMapping("/back")
public class IndexController {

    @Resource(name = "ApiArticleService")
    private ArticleService articleService;

    @GetMapping({"/index", "/home", "/", ""})
    public String index(){
        return "redirect:/back/articles";
    }

    @GetMapping("/about")
    public String about(){
        return OysterBackConfig.themePath+"about";
    }

}
