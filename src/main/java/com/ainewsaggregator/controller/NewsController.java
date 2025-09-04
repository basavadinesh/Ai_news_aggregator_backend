package com.ainewsaggregator.controller;

import com.ainewsaggregator.entity.NewsArticle;
import com.ainewsaggregator.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/fetch")
    public String fetchNews() {
        newsService.fetchAndStoreNews();
        return "News fetched and stored successfully!";
    }

    @GetMapping("/all")
    public List<NewsArticle> getAllNews() {
        return newsService.getAllNewsChronologically();
    }
}