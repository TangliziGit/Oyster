package org.tanglizi.blog.dto.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;
    
    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Boolean allowComment;

    @Column
    private Integer hit;

    @Column
    private Date createTimestamp;

    @Column
    private Date lastModifiedTimestamp;

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", content='" + content + '\'' +
                ", allowComment=" + allowComment +
                ", hit=" + hit +
                ", createTimestamp=" + createTimestamp +
                ", lastModifiedTimestamp=" + lastModifiedTimestamp +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }
}
