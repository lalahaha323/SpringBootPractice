package com.example.springbootmybatistx.service;

import com.example.springbootmybatistx.dao.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    //@Transactional
    public void transfer() throws RuntimeException {
        accountMapper.update(90, 1);
        int i = 1/0;
        accountMapper.update(110, 2);
    }
}
