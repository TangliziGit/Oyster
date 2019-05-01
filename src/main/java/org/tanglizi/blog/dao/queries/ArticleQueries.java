package org.tanglizi.blog.dao.queries;

import org.tanglizi.blog.dao.ExampleQuery;

import java.util.List;

public class ArticleQueries extends ExampleQuery {
    @QueryWord(column = "title", type = MatchType.LIKE)
    private List<String> titleLikeList;

    @QueryWord(column = "content", type = MatchType.LIKE)
    private List<String> contentLikeList;

    public List<String> getTitleLikeList() {
        return titleLikeList;
    }

    public void setTitleLikeList(List<String> titleLikeList) {
        this.titleLikeList = titleLikeList;
    }

    public List<String> getContentLikeList() {
        return contentLikeList;
    }

    public void setContentLikeList(List<String> contentLikeList) {
        this.contentLikeList = contentLikeList;
    }
}
