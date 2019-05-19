package org.tanglizi.oyster.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tanglizi.oyster.api.model.RESTfulResponse;
import org.tanglizi.oyster.api.services.TagService;
import org.tanglizi.oyster.common.entities.Tag;
import org.tanglizi.oyster.common.model.TagModel;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/api/v1/tags")
public class TagController {

    @Resource(name = "ApiTagService")
    private TagService tagService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<RESTfulResponse<List<Tag>>> getAllTags(){
        List<Tag> tags=tagService.getAllTags();
        if (tags==null || tags.size()==0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RESTfulResponse.fail("no category exists"));

        RESTfulResponse<List<Tag>> response=RESTfulResponse.ok();
        response.setData(tags);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RESTfulResponse<TagModel>> getTagModel(@PathVariable("id") Integer tagId){
        TagModel tagModel=tagService.getTagModel(tagId);
        if (tagModel == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(RESTfulResponse.fail("could not find the tag"));

        RESTfulResponse<TagModel> response=RESTfulResponse.ok();
        response.setData(tagModel);

        return ResponseEntity.ok(response);
    }
}
