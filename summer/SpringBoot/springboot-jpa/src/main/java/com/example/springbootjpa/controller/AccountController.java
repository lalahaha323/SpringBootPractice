package com.example.springbootjpa.controller;


import com.example.springbootjpa.dao.AccountDao;
import com.example.springbootjpa.enty.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountDao accountDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return accountDao.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@RequestBody HashMap<String, String> map) {
        double money = Double.parseDouble(map.get("money"));
        int id = Integer.parseInt(map.get("id"));
        String name = map.get("name");
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        Account account1 = accountDao.saveAndFlush(account);

        return account1.toString();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestBody HashMap<String, String> map) {
        double money = Double.parseDouble(map.get("money"));
        String name = map.get("name");
        Account account = new Account();
        account.setMoney(money);
        account.setName(name);
        Account account1 = accountDao.save(account);
        return account1.toString();
    }
}
