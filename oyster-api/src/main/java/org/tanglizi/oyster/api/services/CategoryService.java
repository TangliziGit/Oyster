package org.tanglizi.oyster.api.services;

import org.tanglizi.oyster.common.entities.Category;
import org.tanglizi.oyster.common.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    CategoryModel getCategoryModel(Integer categoryId);
}
