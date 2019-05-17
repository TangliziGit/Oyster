package org.tanglizi.oyster.front.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.oyster.common.entities.Article;
import org.tanglizi.oyster.front.configurations.OysterFrontConfig;
import org.tanglizi.oyster.front.services.ArticleService;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class IndexController {

    @Resource(name = "FrontArticleService")
    private ArticleService articleService;

    @GetMapping({"/index", "/home", "/"})
    public String index(@RequestParam(name = "page", defaultValue = "0") Integer page,
                        @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                        Map<String, Object> map){
        Page<Article> articlePage=articleService.findArticles(page, limit);

        map.put("articles", articlePage.getContent());
        map.put("page", page);
        map.put("limit", limit);
        map.put("pageCount", articlePage.getTotalPages());

        return OysterFrontConfig.themePath +"index";
    }

    @GetMapping("/about")
    public String about(){
        return OysterFrontConfig.themePath +"about";
    }

    @GetMapping("/archives")
    public String archives(@RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "limit", defaultValue = "20") Integer limit,
                           Map<String, Object> map){
        Page<Article> articlePage=articleService.findArticles(page, limit);

        map.put("articles", articlePage.getContent());
        map.put("page", page);
        map.put("limit", limit);
        map.put("pageCount", articlePage.getTotalPages());

        return OysterFrontConfig.themePath +"archives";
    }
}

// org.thymeleaf.exceptions.TemplateInputException: An error happened during template parsing (template: "class path resource [templates/themes/dxx/index.html]")] with root cause
// 注意thymeleaf的模板位置，若thymeleaf渲染出错，则跳过thymeleaf的viewResolver，报错
