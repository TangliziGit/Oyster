package org.tanglizi.oyster.services;

import org.tanglizi.oyster.dto.entities.Category;
import org.tanglizi.oyster.dto.CategoryInfo;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    CategoryInfo getCategoryInfo(Integer categoryId);
}
