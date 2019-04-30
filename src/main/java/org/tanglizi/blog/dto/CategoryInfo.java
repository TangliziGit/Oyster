package org.tanglizi.blog.dto.entities;

import java.util.List;

public class CategoryInfo {
    private Integer id;
    private String name;
    private List<Integer> articlesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getArticlesId() {
        return articlesId;
    }

    public void setArticlesId(List<Integer> articlesId) {
        this.articlesId = articlesId;
    }
}
