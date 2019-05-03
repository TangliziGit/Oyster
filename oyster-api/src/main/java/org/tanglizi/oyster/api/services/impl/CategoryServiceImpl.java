package org.tanglizi.oyster.api.services.impl;

import org.springframework.stereotype.Service;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CategoryService;
import org.tanglizi.oyster.common.dao.repositories.CategoryRepository;
import org.tanglizi.oyster.common.entities.Category;
import org.tanglizi.oyster.common.model.CategoryModel;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public RESTfulResponse<List<Category>> getAllCategoriesResponse() {
        List<Category> categories=categoryRepository.findAll();
        if (categories==null || categories.size()==0)
            return RESTfulResponse.fail("no category exists");

        RESTfulResponse<List<Category>> response=RESTfulResponse.ok();
        response.setPayload(categories);
        return response;
    }

    @Override
    public RESTfulResponse<CategoryModel> getCategoryModelResponse(Integer categoryId) {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if (category==null)
            return RESTfulResponse.fail("could not find the category");

        CategoryModel categoryModel = new CategoryModel(){{
            setId(categoryId);
            setName(category.getName());
            setArticlesId(categoryRepository.findArticleIdsByCategoryId(categoryId));
        }};

        RESTfulResponse<CategoryModel> response=RESTfulResponse.ok();
        response.setPayload(categoryModel);
        return response;
    }
}
