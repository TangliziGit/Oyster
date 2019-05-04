package org.tanglizi.oyster.api.services.impl;

import org.springframework.stereotype.Service;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CategoryService;
import org.tanglizi.oyster.common.dao.repositories.CategoryRepository;
import org.tanglizi.oyster.common.entities.Category;
import org.tanglizi.oyster.common.model.CategoryModel;

import javax.annotation.Resource;
import java.util.List;

@Service("ApiCategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel getCategoryModel(Integer categoryId) {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if (category==null)
            return null;

        CategoryModel categoryModel = new CategoryModel(){{
            setId(categoryId);
            setName(category.getName());
            setArticlesId(categoryRepository.findArticleIdsByCategoryId(categoryId));
        }};

        return categoryModel;
    }
}
