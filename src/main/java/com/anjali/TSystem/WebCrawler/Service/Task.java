package com.anjali.TSystem.WebCrawler.Service;

import com.anjali.TSystem.WebCrawler.Model.DetailedLink;
import com.anjali.TSystem.WebCrawler.Model.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class Task extends RecursiveTask<WebCrawler> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    public WebCrawler webCrawler;
    String url;
    int depth;
    HashSet<String> links = new HashSet<>();
    Task(WebCrawler webCrawler, String url, int depth) {
        this.webCrawler = webCrawler;
        this.url = url;
        this.depth = depth;
    }
    @Override
    protected WebCrawler compute() {
        if (this.depth <=0) {
            return this.webCrawler;
        }
        List<ForkJoinTask<WebCrawler>> resultList = new ArrayList<>();
        if(!links.contains(this.url)) {
            try {
                links.add(this.url);
                DetailedLink detailedLink = new DetailedLink();
                LOGGER.info("Connecting to given url through Jsoup");
                Document document = Jsoup.connect(this.url).get();
                Elements linksOnPage = document.select("a[href]");
                Elements images = document.select("img");
                Elements title = document.select("title");
                detailedLink.setPage_title(title.text());
                detailedLink.setImage_count(images.size());
                detailedLink.setPage_link(this.url);
                detailedLink.setDepth(this.depth);
                detailedLink.setLinks_count(linksOnPage.size());
                this.webCrawler.setTotal_images(images.size() + this.webCrawler.getTotal_images());
                this.webCrawler.setTotal_links(linksOnPage.size() + this.webCrawler.getTotal_links());
                this.webCrawler.getDetailedLinks().add(detailedLink);
                this.depth--;
                for (Element link : linksOnPage) {
                    LOGGER.info("Creating a recusive Task");
                    Task recursiveTask = new Task(new WebCrawler(), link.attr("abs:href"), this.depth);
                    LOGGER.info("Forking the tasks");
                    resultList.add(recursiveTask.fork());
                }

            } catch (Exception ex) {
                LOGGER.error("For url: " + url + " error message is: " + ex.getMessage());
            }
        }
        for (ForkJoinTask<WebCrawler> result : resultList) {
            LOGGER.info("Get the result of recursive tasks");
            WebCrawler wc = result.join();
            this.webCrawler.getDetailedLinks().addAll(wc.getDetailedLinks());
            this.webCrawler.setTotal_links(wc.getTotal_links() + this.webCrawler.getTotal_links());
            this.webCrawler.setTotal_images(wc.getTotal_images() + this.webCrawler.getTotal_images());
        }
        return this.webCrawler;
    }
}