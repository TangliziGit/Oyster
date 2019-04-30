package org.tanglizi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tanglizi.blog.dto.TagInfo;
import org.tanglizi.blog.dto.entities.Tag;
import org.tanglizi.blog.services.TagService;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    @ResponseBody
    public List<Tag> getAllTags(){
        return tagService.findAllTags();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TagInfo tagInfo(@PathVariable("id") Integer tagId){
        return tagService.getTagInfo(tagId);
    }
}
