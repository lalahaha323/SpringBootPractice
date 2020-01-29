package com.example.demo.controller;

import com.example.demo.utils.EsUtil;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author lala
 */
@RestController
public class EsDeleteController {

    @GetMapping("/delete/book")
    public void deleteBook() {
        RestHighLevelClient client = EsUtil.create();

        DeleteRequest request = new DeleteRequest(
                "book",
                "_doc",
                "1"
        );

        try {
            DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
