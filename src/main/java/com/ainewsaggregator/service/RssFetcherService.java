package com.ainewsaggregator.service;

import com.ainewsaggregator.entity.RssArticle;
import com.ainewsaggregator.repository.RssArticleRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssFetcherService {

    @Autowired
    private RssArticleRepository rssArticleRepository;

    public void fetchAndStoreRssArticles(String rssUrl, String category) {
        try {
            URL feedSource = new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));

            List<RssArticle> rssArticles = new ArrayList<>();

            for (SyndEntry entry : feed.getEntries()) {
                RssArticle rssArticle = new RssArticle(
                        entry.getTitle(),
                        entry.getLink(),
                        entry.getDescription() != null ? entry.getDescription().getValue() : "",
                        entry.getPublishedDate(),
                        category
                );
                rssArticles.add(rssArticle);
            }

            rssArticleRepository.saveAll(rssArticles);
            System.out.println("Saved " + rssArticles.size() + " RSS articles into rss_articles collection.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
