package org.tanglizi.blog.services;

import org.springframework.data.domain.Page;
import org.tanglizi.blog.entities.Category;
import org.tanglizi.blog.entities.CategoryInfo;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    CategoryInfo getCategoryInfo(Integer categoryId);
}
