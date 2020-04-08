package com.anjali.TSystem.WebCrawler.Service;

import com.anjali.TSystem.WebCrawler.Model.CrawlerRequest;
import com.anjali.TSystem.WebCrawler.Model.WebCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CrawlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerService.class);

    public Object searchNewRequest(CrawlerRequest request) {
        LOGGER.info("Validation Request:");
        HashMap<String, Object> errorMap = validation(request);
        if(errorMap.size() != 0){
            LOGGER.error("Validation failed with error {} :", errorMap);
            return errorMap;
        }
        LOGGER.info("Validation successful");
        URLService service = new URLService(request.getUrl(),request.getDepth());
        WebCrawler webCrawler = service.startProcess();
        return webCrawler;
    }


    private HashMap<String, Object> validation(CrawlerRequest request) {
        HashMap<String,Object> errorMap = new HashMap<>();
        if (request == null){
            errorMap.put("error","request can not be null");
            return errorMap;
        }else {
            if(request.getUrl() == null){
                errorMap.put("error","baseUrl can not be null");
            }
            if(request.getDepth() < 0){
                errorMap.put("error","depth can not be negative");
            }
        }
        return errorMap;
    }
}
