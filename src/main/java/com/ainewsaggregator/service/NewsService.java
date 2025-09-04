package com.ainewsaggregator.service;

import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ainewsaggregator.entity.NewsArticle;
import com.ainewsaggregator.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NewsService {

    @Value("${newsapi.url}")
    private String apiUrl;

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void fetchAndStoreNews() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray articles = jsonResponse.getJSONArray("articles");

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        for (int i = 0; i < articles.length(); i++) {
            JSONObject article = articles.getJSONObject(i);

            NewsArticle newsArticle = new NewsArticle();
            newsArticle.setTitle(article.getString("title"));
            newsArticle.setDescription(article.optString("description", ""));
            newsArticle.setUrl(article.getString("url"));
            newsArticle.setSource(article.getJSONObject("source").getString("name"));

            String publishedAtStr = article.getString("publishedAt");
            newsArticle.setPublishedAt(LocalDateTime.parse(publishedAtStr, formatter));

            newsRepository.save(newsArticle);
        }
    }

    public java.util.List<NewsArticle> getAllNewsChronologically() {
        return newsRepository.findAllByOrderByPublishedAtDesc();
    }
}
