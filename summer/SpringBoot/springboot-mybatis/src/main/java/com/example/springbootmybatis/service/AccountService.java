package com.example.springbootmybatis.service;

import com.example.springbootmybatis.dao.AccountMapper;
import com.example.springbootmybatis.enty.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountService {

//    @Autowired
    @Resource
    private AccountMapper accountMapper;

    public int add(String name, double money) {
        return accountMapper.add(name, money);
    }

    public int update(String name, double money, int id) {
        return accountMapper.update(name, money, id);
    }

    public int delete(int id) {
        return accountMapper.delete(id);
    }

    public Account findAccount(int id) {
        return accountMapper.findAccount(id);
    }

    public List<Account> findAccountList() {
        return accountMapper.findAccountList();
    }

}
