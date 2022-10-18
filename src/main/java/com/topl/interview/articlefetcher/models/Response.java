package com.topl.interview.articlefetcher.models;

import java.util.List;

public class Response {
    private Integer totalArticles;
    private List<Article> articles;

    public Integer getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(Integer totalArticles) {
        this.totalArticles = totalArticles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
