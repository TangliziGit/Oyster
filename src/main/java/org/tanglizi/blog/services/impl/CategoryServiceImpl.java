package org.tanglizi.blog.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tanglizi.blog.dao.repositories.CategoryRepository;
import org.tanglizi.blog.entities.Category;
import org.tanglizi.blog.entities.CategoryInfo;
import org.tanglizi.blog.services.CategoryService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryInfo getCategoryInfo(Integer categoryId) {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        if (category==null) return null;

        CategoryInfo categoryInfo=new CategoryInfo();
        categoryInfo.setId(categoryId);
        categoryInfo.setName(category.getName());
        categoryInfo.setArticlesId(
                categoryRepository.findArticleIdsById(categoryId)
        );

        return categoryInfo;
    }
}
