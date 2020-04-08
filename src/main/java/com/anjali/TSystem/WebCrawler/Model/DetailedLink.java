package com.anjali.TSystem.WebCrawler.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class DetailedLink {
    String page_title;
    String page_link;
    int image_count;
    int depth;
    int links_count;

    public DetailedLink() {
    }

    public DetailedLink(String page_title, String page_link, int image_count, int depth, int links_count) {
        this.page_title = page_title;
        this.page_link = page_link;
        this.image_count = image_count;
        this.depth = depth;
        this.links_count = links_count;
    }
}
