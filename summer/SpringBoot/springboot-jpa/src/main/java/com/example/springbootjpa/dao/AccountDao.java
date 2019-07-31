package com.example.springbootjpa.dao;

import com.example.springbootjpa.enty.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Integer> {
}
