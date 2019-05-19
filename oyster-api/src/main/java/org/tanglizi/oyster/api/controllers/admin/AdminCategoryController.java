package org.tanglizi.oyster.api.controllers.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scripting.support.RefreshableScriptTargetSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.oyster.api.configurations.OysterApiConfig;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.CategoryService;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.entities.Category;
import org.tanglizi.oyster.common.utils.GlobalCacheKit;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Resource(name = "ApiCategoryService")
    private CategoryService categoryService;

    public ResponseEntity<RESTfulResponse> addCategory(Category category,
                                                       @RequestParam("_csrf_token") String csrfToken){
        GlobalCacheKit globalCache=GlobalCacheKit.getCacheSingleton();
        RESTfulResponse response=null;

        if (null == csrfToken || !OysterCommonConfig.CRSF_TOKEN.equals(globalCache.get(csrfToken)))
            response = RESTfulResponse.fail();

        if (StringUtils.isBlank(category.getName()))
            response = RESTfulResponse.fail("Category name should not be empty");

        if (category.getName().length() > OysterApiConfig.CATEGORY_LENGTH)
            response = RESTfulResponse.fail("Length of the name is too long");

        if (null != response)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);

        categoryService.saveCategory(category);

        return ResponseEntity.ok(RESTfulResponse.ok("save new category successfully"));
    }

    public ResponseEntity<RESTfulResponse> deleteCategory(@RequestParam("categoryId") Integer categoryId,
                                                          @RequestParam("_csrf_token") String csrfToken){
        GlobalCacheKit globalCache = GlobalCacheKit.getCacheSingleton();

        if (null == csrfToken || !OysterCommonConfig.CRSF_TOKEN.equals(globalCache.get(csrfToken)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(RESTfulResponse.fail());

        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(RESTfulResponse.ok());
    }
}
