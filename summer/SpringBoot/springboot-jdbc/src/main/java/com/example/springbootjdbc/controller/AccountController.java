package com.example.springbootjdbc.controller;


import com.example.springbootjdbc.enty.Account;
import com.example.springbootjdbc.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountService.findAccountList();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return accountService.findAccountById(id);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public String updateAccount(@RequestBody HashMap<String, String> map) {
        int id = Integer.parseInt(map.get("id"));
        double money = Double.parseDouble(map.get("money"));
        String name = map.get("name");
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t = accountService.update(account);
        if(t == 1) {
            return account.toString();
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestBody HashMap<String, String> map) {
        Account account = new Account();
        double money = Double.parseDouble(map.get("money"));
        String name = map.get("name");
        account.setMoney(money);
        account.setName(name);
        int t = accountService.add(account);
        if(t == 1) {
            return account.toString();
        } else {
            return "fail";
        }
    }
}
