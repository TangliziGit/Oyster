package org.tanglizi.oyster.services;

import org.tanglizi.oyster.entities.Category;
import org.tanglizi.oyster.model.CategoryInfo;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    CategoryInfo getCategoryInfo(Integer categoryId);
}
