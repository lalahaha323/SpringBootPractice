package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Controller
public class HelloController {


//    @RequestMapping({"/","/index.html"})
//    public String index() {
//        return "index";
//    }
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map) {
        map.put("hello", "你好");
        //classpath:/templates/success.html
        return "success";
    }
}
