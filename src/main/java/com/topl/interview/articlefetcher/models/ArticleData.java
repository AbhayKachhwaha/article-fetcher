package com.topl.interview.articlefetcher.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ArticleData {
    @JsonIgnore
    private Integer keyWordCount;
    private Integer totalWords;
    private Map<String, Integer> wordCountMap;

    public Integer getKeyWordCount() {
        return keyWordCount;
    }

    public void setKeyWordCount(Integer keyWordCount) {
        this.keyWordCount = keyWordCount;
    }

    public Map<String, Integer> getWordCountMap() {
        return wordCountMap;
    }

    public void setWordCountMap(Map<String, Integer> wordCountMap) {
        this.wordCountMap = wordCountMap;
    }

    public Integer getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(Integer totalWords) {
        this.totalWords = totalWords;
    }
}
