package com.topl.interview.articlefetcher.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.topl.interview.articlefetcher.models.Article;
import com.topl.interview.articlefetcher.models.ArticleData;
import com.topl.interview.articlefetcher.models.Response;
import com.topl.interview.articlefetcher.models.Source;

@RestController
public class ArticleController {

    @Value("${apiKey}")
    String key;

    private String uri = "https://gnews.io/api/v4/search?&q=";
    private String staticTokenString = "&token=";

    @GetMapping("/article/{searchText}")
    @Cacheable(value = "#searchText", key = "#searchText")
    public ResponseEntity<List<Article>> getArticles(@PathVariable String searchText) {
        uri = uri.concat(searchText).concat(staticTokenString).concat(key);
        // System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject(uri, Response.class);

        if (response == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        List<Article> articles = response.getArticles();
        for (Article article : articles) {
            ArticleData articleData = new ArticleData();
            articleData.setWordCountMap(article.wordCountMap());
            articleData.setTotalWords(article.contentLength());
            article.setMetaData(articleData);
        }

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/article/{searchText}/{max}")
    @Cacheable(value = "#searchText+#max", key = "#searchText+#max")
    public ResponseEntity<List<Article>> getNArticles(@PathVariable String searchText, @PathVariable Integer max) {
        uri = uri.concat(searchText).concat("&max=").concat(max.toString()).concat(staticTokenString).concat(key);

        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject(uri, Response.class);

        if (response == null)
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        List<Article> articles = response.getArticles();
        for (Article article : articles) {
            ArticleData articleData = new ArticleData();
            articleData.setWordCountMap(article.wordCountMap());
            articleData.setTotalWords(article.contentLength());
            article.setMetaData(articleData);
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/article/{searchText}/author/{authorName}")
    @Cacheable(value = "#searchText+#authorName", key = "#searchText+#authorName")
    public ResponseEntity<Object[]> getArticlesByAuthor(@PathVariable String searchText,
            @PathVariable String authorName) {
        uri = uri.concat(searchText).concat("&token=").concat(key);

        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject(uri, Response.class);

        if (response == null)
            return new ResponseEntity<>(new Object[0], HttpStatus.BAD_REQUEST);

        List<Article> articles = response.getArticles();
        for (Article article : articles) {
            ArticleData articleData = new ArticleData();
            articleData.setWordCountMap(article.wordCountMap());
            articleData.setTotalWords(article.contentLength());
            article.setMetaData(articleData);
        }
        
        Predicate<Article> byAuthor = article -> article.getSource().getName().equals(authorName);

        return new ResponseEntity<>(articles.stream().filter(byAuthor).toArray(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Article>> alternateSearchText() {
        ArrayList<Source> sources = new ArrayList<>();
        sources.add(new Source("test author", "https://www.example.com"));
        sources.add(new Source("test author 2", "https://www.example.com"));
        Article article = new Article("test", "test description", "test content", "https://www.example.com",
                "testimgLink", "testdate", sources);

        List<Article> articles = new ArrayList<>();
        articles.add(article);
        articles.add(article);

        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
