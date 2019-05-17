package org.tanglizi.oyster.front.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.common.entities.Article;
import org.tanglizi.oyster.front.configurations.FlexmarkConfig;
import org.tanglizi.oyster.front.configurations.OysterFrontConfig;
import org.tanglizi.oyster.front.exceptions.PageNotFoundException;
import org.tanglizi.oyster.front.services.ArticleService;
import org.tanglizi.oyster.front.services.CommentService;
import org.tanglizi.oyster.front.services.TagService;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private Logger logger= LoggerFactory.getLogger(ArticleController.class);

    @Resource(name = "FrontArticleService")
    private ArticleService articleService;

    @Resource(name = "FrontCommentService")
    private CommentService commentService;

    @Resource(name = "FrontTagService")
    private TagService tagService;

    @GetMapping("/{id}")
    public String arcticlesPage(@PathVariable("id") Integer articleId,
                                @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                @RequestParam(value = "limit", defaultValue = "20") int limit,
                                Map<String, Object> map)
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
                commentService.findCommentsBtArticleId(articleId, pageNumber, limit)
        );
        map.put("tags",
                tagService.findTagsByArticleId(articleId)
        );

        return OysterFrontConfig.themePath +"articles";
    }
}
