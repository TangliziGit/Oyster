package org.tanglizi.oyster.api.controllers.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.api.configurations.OysterApiConfig;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.entities.Article;
import org.tanglizi.oyster.common.utils.SecurityKit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * TODO:
 *  articles        get     done
 *  articles/1      get     done
 *  articles/1      delete  done
 *  articles/1      update  done
 *  articles/1      post    done
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

        RESTfulResponse<List<Article>> response=RESTfulResponse.ok();
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

        RESTfulResponse<Article> response=RESTfulResponse.ok();
        response.setData(article);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{articleId}")
    @ResponseBody
    public ResponseEntity<RESTfulResponse> deleteArticle(
            @PathVariable("articleId") Integer articleId,
            HttpServletRequest request,
            @RequestParam("_csrf_token") String csrfToken){

        logger.info("csrfToken: "+csrfToken);
        RESTfulResponse response=null;
        SecurityKit.SecurityBlockType securityBlockType=SecurityKit.securityBlock(request, csrfToken);

        if (null != securityBlockType)
            response = RESTfulResponse.fail();

        if (null != response)
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(response);

        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(RESTfulResponse.ok());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RESTfulResponse> postArticle(
            Article article,
            HttpServletRequest request,
            @RequestParam("_csrf_token") String csrfToken){

        logger.info("csrfToken: "+csrfToken);
        logger.info(article.toString());
        RESTfulResponse response=null;
        SecurityKit.SecurityBlockType securityBlockType=SecurityKit.securityBlock(request, csrfToken);

        if (null != securityBlockType)
            response = RESTfulResponse.fail();

        if (null == response)
            response=chekcArticleValidity(article);

        if (null != response) {
            logger.info("Error response: "+response.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        articleService.saveArticle(article);

        return ResponseEntity.ok(RESTfulResponse.ok());
    }

    @PostMapping("/{articleId}")
    @ResponseBody
    public ResponseEntity<RESTfulResponse> updateArticle(
            @PathVariable("articleId") Integer articleId,
            Article article,
            HttpServletRequest request,
            @RequestParam("_csrf_token") String csrfToken){

        logger.info("csrfToken: "+csrfToken);
        RESTfulResponse response=null;
        SecurityKit.SecurityBlockType securityBlockType=SecurityKit.securityBlock(request, csrfToken);

        if (null != securityBlockType)
            response = RESTfulResponse.fail();

        if (null !=response && null == articleService.getArticle(articleId))
            response=RESTfulResponse.fail("The article does not exists");

        if (null !=response){
            completeArticle(article);
            System.out.println(article);
            response=chekcArticleValidity(article);
        }

        if (null != response) {
            logger.info("Error response: "+response.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        /*
        * TODO:
        * test this, if there is a conflict
        * */
        article.setArticleId(articleId);
        String errorMessage=articleService.updateArticleReturnErrorMessage(article);

        if (null != errorMessage)
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(RESTfulResponse.fail(errorMessage));

        return ResponseEntity.ok(RESTfulResponse.ok());
    }

    private RESTfulResponse chekcArticleValidity(Article article){
        RESTfulResponse response=null;

        if (StringUtils.isBlank(article.getContent()))
            response=RESTfulResponse.fail("The article can not be empty.");

        if (null == response && article.getContent().length() > OysterApiConfig.ARTICLE_CONTENT_LENGTH)
            response=RESTfulResponse.fail("The article content can not be too large.");

        if (null == response && article.getTitle().length() > OysterApiConfig.ARTICLE_TITLE_LENGTH)
            response=RESTfulResponse.fail("The article title can not be too large.");

        SecurityKit.cleanXSS(article.getContent());
        SecurityKit.cleanXSS(article.getTitle());

        return response;
    }

    private void completeArticle(Article article){
        if (null == article.getArticleId()) return;

        Article oldArticle=articleService.getArticle(article.getArticleId());

        if (null == article.getTitle())
            article.setTitle(oldArticle.getTitle());
        if (null == article.getContent())
            article.setContent(oldArticle.getContent());
        if (null == article.getAllowComment()){
            Boolean oldAllowComment=oldArticle.getAllowComment();
            if (null != oldAllowComment)
                article.setAllowComment(oldAllowComment);
            else
                article.setAllowComment(OysterCommonConfig.defaultAllowComment);
        }
    }
}
