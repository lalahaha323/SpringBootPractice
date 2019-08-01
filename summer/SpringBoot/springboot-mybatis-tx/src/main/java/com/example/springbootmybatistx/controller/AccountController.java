package com.example.springbootmybatistx.controller;


import com.example.springbootmybatistx.service.AccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@MapperScan("com.example.springbootmybatistx.dao")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "transfer", method = RequestMethod.GET)
    public void transfer() {
        accountService.transfer();
    }
}
