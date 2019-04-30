package org.tanglizi.blog.services;

import org.tanglizi.blog.dto.entities.Category;
import org.tanglizi.blog.dto.CategoryInfo;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    CategoryInfo getCategoryInfo(Integer categoryId);
}
