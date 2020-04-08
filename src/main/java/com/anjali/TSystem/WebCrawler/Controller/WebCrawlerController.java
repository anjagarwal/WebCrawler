package com.anjali.TSystem.WebCrawler.Controller;

import com.anjali.TSystem.WebCrawler.Model.CrawlerRequest;
import com.anjali.TSystem.WebCrawler.Service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebCrawlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebCrawlerController.class);

    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping(value = "/crawler", method = RequestMethod.POST)
    public ResponseEntity createCrawler(@RequestBody CrawlerRequest request) {
        LOGGER.info("Request started with base url {} and depth {} : ", request.getUrl(), request.getDepth());
        return new ResponseEntity(crawlerService.searchNewRequest(request), HttpStatus.OK);
    }


}
