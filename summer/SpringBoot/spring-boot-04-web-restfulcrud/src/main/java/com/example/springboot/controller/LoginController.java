package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LoginController {

//    @DeleteMapping
//    @PutMapping
//    @GetMapping(value = "/user/login")
    @PostMapping(value = "/user/login")
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map) {
        if(!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登陆成功
            return "dashboard";
        } else {
            //登录失败
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }
}