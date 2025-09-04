package com.ainewsaggregator.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "rss_articles")
public class RssArticle {

    @Id
    private String id;

    private String title;
    private String link;
    private String description;
    private Date publishedDate;
    private String category;

    public RssArticle() {}

    public RssArticle(String title, String link, String description, Date publishedDate, String category) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.publishedDate = publishedDate;
        this.category = category;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getPublishedDate() { return publishedDate; }
    public void setPublishedDate(Date publishedDate) { this.publishedDate = publishedDate; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}

