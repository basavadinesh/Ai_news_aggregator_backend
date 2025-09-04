package com.ainewsaggregator.repository;

import com.ainewsaggregator.entity.NewsArticle;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NewsRepository extends MongoRepository<NewsArticle, String> {
    List<NewsArticle> findAllByOrderByPublishedAtDesc();
}

