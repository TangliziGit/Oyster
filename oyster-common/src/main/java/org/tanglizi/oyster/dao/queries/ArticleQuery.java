package org.tanglizi.oyster.dao.queries;

import org.tanglizi.oyster.dao.AbstractQuery;

import java.util.List;

public class ArticleQuery extends AbstractQuery {
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

