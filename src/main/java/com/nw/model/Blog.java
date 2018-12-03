package com.nw.model;

import java.util.Date;

public class Blog {
    private Integer id;

    private String title;

    private String content;

    private Integer userId;

    private Date pubDate;

    public Blog(Integer id, String title, String content, Integer userId, Date pubDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.pubDate = pubDate;
    }

    public Blog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}