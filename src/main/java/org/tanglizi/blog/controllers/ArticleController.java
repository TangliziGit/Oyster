package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.blog.configurations.BlogConfig;
import org.tanglizi.blog.configurations.FlexmarkConfig;
import org.tanglizi.blog.dto.entities.Article;
import org.tanglizi.blog.exceptions.PageNotFoundException;
import org.tanglizi.blog.services.ArticleService;
import org.tanglizi.blog.services.CommentService;

import java.util.Map;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public String arcticlesPage(@PathVariable("id") Integer articleId,
                                @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                @RequestParam(value = "limit", defaultValue = "20") int limit,
                                Map map)
        throws PageNotFoundException{
        Article article = articleService.findArticleById(articleId);
        if (article==null)
            throw new PageNotFoundException();

        long articleCount = articleService.findArticleCount();

        map.put("article", article);
        map.put("articleCount", articleCount);
        map.put("markdownContent", FlexmarkConfig.FlexmarkParser.parse(article.getContent()));
        map.put("comments",
                commentService.findCommentsByArticleId(articleId, pageNumber, limit).getContent()
        );

        System.out.println(map);

        return BlogConfig.THEME_PATH+"articles";
    }
}
