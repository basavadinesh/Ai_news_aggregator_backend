package com.ainewsaggregator.controller;

import com.ainewsaggregator.service.RssFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rss")
public class RssController {

    @Autowired
    private RssFetcherService rssFetcherService;

    @PostMapping("/fetch")
    public String fetchRss(@RequestParam String url, @RequestParam String category) {
        rssFetcherService.fetchAndStoreRssArticles(url, category);
        return "RSS Fetch triggered for category: " + category;
    }


}

