package org.tanglizi.oyster.api.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tanglizi.oyster.api.services.ArticleService;
import org.tanglizi.oyster.common.dao.repositories.ArticleRepository;
import org.tanglizi.oyster.common.entities.Article;

import javax.annotation.Resource;

/*
 * TODO:
 *  articles        get
 *  articles/1      get
 *  articles/1      delete
 *  articles/1      update
 *  articles/1      post
 *
 * */
@Controller
@RequestMapping("/api/v1/admin/articles")
public class AdminArticleController {
    @Resource(name = "ApiArticleService")
    private ArticleService articleService;

}
