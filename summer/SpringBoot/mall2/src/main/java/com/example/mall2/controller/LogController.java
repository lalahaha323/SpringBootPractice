package com.example.mall2.controller;

/**
 * @author lala
 */

import com.example.mall2.pojo.Result;
import com.example.mall2.service.LogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录注册
 */

@RestController
@RequestMapping("/user")
public class LogController {

    @Autowired
    private LogService logService;
    //登录
    @PostMapping("/login")
    public Result userLogin(@RequestBody Map<Object, Object> map) {
        String user_name = (String)(map.get("user_name"));
        String user_password = (String)(map.get("user_password"));
        logService.userLogin(user_name, user_password);
        return null;
    }
    //发送验证码
    @PostMapping("/sendSecurityCode")
    public Result getSecurityCode(@RequestBody String user_email) {
        Result result = logService.findEmail(user_email);
        return result;
    }
    //注册
    @PostMapping("/register")
    public Result userRegister(@RequestBody Map<Object, Object> map) {
        Result result = logService.userRegister(map);
        return null;
    }

}
