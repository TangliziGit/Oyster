package org.tanglizi.oyster.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CategoryService;
import org.tanglizi.oyster.common.model.CategoryModel;
import org.tanglizi.oyster.common.entities.Category;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Resource(name = "ApiCategoryService")
    private CategoryService categoryService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<RESTfulResponse<List<Category>>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        if (categories==null || categories.size()==0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("no category exists"));

        RESTfulResponse<List<Category>> response=RESTfulResponse.ok();
        response.setData(categories);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RESTfulResponse<CategoryModel>> getCategoryModel(@PathVariable("id") Integer categoryId){
        CategoryModel categoryModel=categoryService.getCategoryModel(categoryId);
        if (categoryModel==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("could not find the category"));

        RESTfulResponse<CategoryModel> response=RESTfulResponse.ok();
        response.setData(categoryModel);

        return ResponseEntity.ok(response);
    }
}
