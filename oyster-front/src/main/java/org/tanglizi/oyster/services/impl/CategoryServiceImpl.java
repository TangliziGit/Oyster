package org.tanglizi.oyster.services.impl;

import org.springframework.stereotype.Service;
import org.tanglizi.oyster.dao.repositories.CategoryRepository;
import org.tanglizi.oyster.dto.entities.Category;
import org.tanglizi.oyster.dto.CategoryInfo;
import org.tanglizi.oyster.services.CategoryService;

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

        return new CategoryInfo(){{
            setId(categoryId);
            setName(category.getName());
            setArticlesId(
                    categoryRepository.findArticleIdsById(categoryId)
            );
        }};
    }
}
