package com.anjali.TSystem.WebCrawler.Service;

import com.anjali.TSystem.WebCrawler.Model.WebCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ForkJoinPool;

@Service
public class URLService {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLService.class);
    String url;
    int depth;
    public URLService(){ }
    URLService(String url, int depth)
    {
        this.url = url;
        this.depth = depth;
    }
    protected WebCrawler startProcess() {
        LOGGER.info("Creating fork join pool");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        WebCrawler responseWebCrawler = new WebCrawler();
        Task task = new Task(responseWebCrawler,this.url,this.depth);
        forkJoinPool.execute(task);
        responseWebCrawler = task.join();
        return responseWebCrawler;
    }
}
