package org.tanglizi.oyster.api.controllers.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tanglizi.oyster.api.configurations.OysterApiConfig;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.TagService;
import org.tanglizi.oyster.common.configurations.OysterCommonConfig;
import org.tanglizi.oyster.common.entities.Tag;
import org.tanglizi.oyster.common.utils.GlobalCacheKit;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/tags")
public class AdminTagController {

    @Resource(name = "ApiTagService")
    private TagService tagService;

    public ResponseEntity<RESTfulResponse> addTag(Tag tag,
                                                  @RequestParam("_csrf_token") String csrfToken){
        GlobalCacheKit globalCache=GlobalCacheKit.getCacheSingleton();
        RESTfulResponse response=null;

        if (null == csrfToken || !OysterCommonConfig.CRSF_TOKEN.equals(globalCache.get(csrfToken)))
            response = RESTfulResponse.fail();

        if (StringUtils.isBlank(tag.getName()))
            response = RESTfulResponse.fail("Tag name should not be empty");

        if (tag.getName().length() > OysterApiConfig.CATEGORY_LENGTH)
            response = RESTfulResponse.fail("Length of the name is too long");

        if (null != response)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);

        tagService.saveTag(tag);

        return ResponseEntity.ok(RESTfulResponse.ok("save new tag successfully"));
    }

    public ResponseEntity<RESTfulResponse> deleteTag(@RequestParam("tagId") Integer tagId,
                                                     @RequestParam("_csrf_token") String csrfToken){
        GlobalCacheKit globalCache = GlobalCacheKit.getCacheSingleton();

        if (null == csrfToken || !OysterCommonConfig.CRSF_TOKEN.equals(globalCache.get(csrfToken)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail());

        tagService.deleteTag(tagId);
        return ResponseEntity.ok(RESTfulResponse.ok());
    }
}
