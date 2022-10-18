package com.topl.interview.articlefetcher.models;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Article {
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publishedAt;
    private Source source;
    @JsonIgnore
    private List<Source> sources;
    private ArticleData articleData;

    public Article(String title, String description, String content, String url, String image, String publishedAt,
            List<Source> sources) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
        this.sources = sources;
    }

    public Article(String title, String description, String content, String url, String image, String publishedAt,
            Source source) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
        this.source = source;
    }

    public Article() {
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public ArticleData getMetaData() {
        return articleData;
    }

    public void setMetaData(ArticleData articleData) {
        this.articleData = articleData;
    }

    public Integer keyWordCount(String keyWord) {
        int count = 0;

        String[] arrayOfWords = this.content.split(" ");
        for (String word : arrayOfWords) {
            if (word.toLowerCase().equals(keyWord)) {
                count++;
            }
        }
        return count;
    }

    public Map<String, Integer> wordCountMap() {
        String[] arrayOfWords = this.content.split(" ");
        Map<String, Integer> map = new Hashtable<>();

        for (int i = 0; i < arrayOfWords.length; i++) {
            if (map.containsKey(arrayOfWords[i])) {
                map.put(arrayOfWords[i], map.get(arrayOfWords[i]) + 1);
            } else {
                map.put(arrayOfWords[i], 1);
            }
        }

        return map;
    }

    public Integer contentLength() {
        String[] arrayOfWords = this.content.split(" ");
        return arrayOfWords.length;
    }
}
