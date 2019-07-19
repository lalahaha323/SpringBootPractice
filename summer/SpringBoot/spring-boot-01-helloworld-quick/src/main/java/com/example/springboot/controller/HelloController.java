package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//这个累的所有方法返回的数据直接写给浏览器,如果是对象转给json数据
//@ResponseBody
//@Controller
@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello world quick!";
    }

    //RESTAPI的方式
}
