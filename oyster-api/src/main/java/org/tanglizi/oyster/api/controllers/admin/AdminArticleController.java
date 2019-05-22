package org.tanglizi.oyster.api.controllers.admin;

import org.apache.catalina.WebResourceRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.common.dao.repositories.ArticleRepository;
import org.tanglizi.oyster.common.entities.Article;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/*
 * TODO:
 *  articles        get
 *  articles/1      get
 *  articles/1      delete
 *  articles/1      update
 *  articles/1      post
 *
 * 1. csrf
 * 2. referer
 * 3. frequent
 * */
@Controller
@RequestMapping("/api/v1/admin/articles")
public class AdminArticleController {
    @Resource(name = "ApiArticleService")
    private ArticleService articleService;

    private Logger logger= LoggerFactory.getLogger(AdminArticleController.class);

    @GetMapping
    @ResponseBody
    public ResponseEntity<RESTfulResponse<List<Article>>> getAllArticle(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("limit") int limit){

        List<Article> articles=articleService.getArticlePage(pageNumber, limit).getContent();

        if (null == articles || articles.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("No article exists"));

        RESTfulResponse response=RESTfulResponse.ok();
        response.setData(articles);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{articleId}")
    @ResponseBody
    public ResponseEntity<RESTfulResponse<Article>> getArticle(
            @PathVariable("articleId") Integer articleId){

        Article article=articleService.getArticle(articleId);

        if (null == article)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("No article exists"));

        RESTfulResponse response=RESTfulResponse.ok();
        response.setData(article);

        return ResponseEntity.ok(response);
    }

}
