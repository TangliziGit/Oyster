package org.tanglizi.oyster.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.configurations.OysterFrontConfig;
import org.tanglizi.oyster.configurations.FlexmarkConfig;
import org.tanglizi.oyster.entities.Article;
import org.tanglizi.oyster.exceptions.PageNotFoundException;
import org.tanglizi.oyster.services.ArticleService;
import org.tanglizi.oyster.services.CommentService;
import org.tanglizi.oyster.services.TagService;

import java.util.Map;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private Logger logger= LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;

    @GetMapping("/{id}")
    public String arcticlesPage(@PathVariable("id") Integer articleId,
                                @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                @RequestParam(value = "limit", defaultValue = "20") int limit,
                                Map map)
        throws PageNotFoundException{
        Article article = articleService.findArticleById(articleId);
        if (article==null)
            throw new PageNotFoundException();

        Integer nextArticleId=articleService.findNextArticleId(articleId);
        Integer prevArticleId=articleService.findPrevArticleId(articleId);

        map.put("article", article);
        map.put("markdownContent", FlexmarkConfig.FlexmarkParser.parse(article.getContent()));
        map.put("nextArticleId", nextArticleId);
        map.put("prevArticleId", prevArticleId);
        map.put("comments",
                commentService.findCommentPageByArticleId(articleId, pageNumber, limit).getContent()
        );
        map.put("tags",
                tagService.findTagsByArticleId(articleId)
        );

        return OysterFrontConfig.themePath +"articles";
    }
}
