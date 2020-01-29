package com.example.demo.controller;

import com.example.demo.utils.EsUtil;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lala
 */

@RestController
public class EsUpdateController {

    @GetMapping("/update/book")
    public void updateBook() {
        RestHighLevelClient client = EsUtil.create();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "馥馥");
        jsonMap.put("author", "dada");

        UpdateRequest updateRequest = new UpdateRequest(
                "book",
                "_doc",
                "1"
        ).doc(jsonMap);
        try {
            client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
