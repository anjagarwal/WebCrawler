package com.anjali.TSystem.WebCrawler.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class CrawlerRequest {
    String url;
    int depth;

    public CrawlerRequest() {
    }

    public CrawlerRequest(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }
}