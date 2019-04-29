package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tanglizi.blog.entities.Category;
import org.tanglizi.blog.entities.CategoryInfo;
import org.tanglizi.blog.services.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ResponseBody
    public List<Category> getAllCategories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CategoryInfo categoryInfo(@PathVariable("id") Integer categoryId){
        return categoryService.getCategoryInfo(categoryId);
    }
}
