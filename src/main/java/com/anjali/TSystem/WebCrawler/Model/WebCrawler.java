package com.anjali.TSystem.WebCrawler.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Service
public class WebCrawler {
    int total_links;
    int total_images;
    private List<DetailedLink> detailedLinks;

    public WebCrawler() {
        this.total_links = 0;
        this.total_images = 0;
        this.detailedLinks =  new LinkedList<>();
    }
}
