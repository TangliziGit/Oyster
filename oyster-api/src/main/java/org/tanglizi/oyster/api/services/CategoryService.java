package org.tanglizi.oyster.api.services;

import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.common.entities.Category;
import org.tanglizi.oyster.common.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    RESTfulResponse<List<Category>> getAllCategoriesResponse();
    RESTfulResponse<CategoryModel> getCategoryModelResponse(Integer categoryId);
}
