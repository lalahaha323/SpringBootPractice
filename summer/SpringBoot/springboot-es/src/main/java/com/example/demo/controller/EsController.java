package com.example.demo.controller;

import com.example.demo.utils.EsUtil;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.Map;

/**
 * @author lala
 */

@RestController
public class EsController {
    @GetMapping("/get/book")
    public void getBook() {
        RestHighLevelClient client = EsUtil.create();

        GetRequest getRequest = new GetRequest(
                "book",
                "_doc",
                "1"
        );

        try {
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

            String index = getResponse.getIndex();
            String type = getResponse.getType();
            String id = getResponse.getId();
            if(getResponse.isExists()) {
                long version = getResponse.getVersion();
                String sourceAsString = getResponse.getSourceAsString();
                Map<String, Object> sourceAsMAp = getResponse.getSourceAsMap();
                byte[] sourceAsBytes = getResponse.getSourceAsBytes();
            } else {
                System.out.println("不存在");
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}