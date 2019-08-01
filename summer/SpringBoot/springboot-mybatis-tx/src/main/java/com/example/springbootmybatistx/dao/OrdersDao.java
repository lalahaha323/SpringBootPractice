package com.example.springbootmybatistx.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


public class OrdersDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void reduceMoney() {
        String sql = "update account1 set salary=salary-? where username=?";
        jdbcTemplate.update(sql, 10, "lala");
    }

    public void addMoney() {
        String sql = "update account1 set salary=salary+? where username=?";
        jdbcTemplate.update(sql, 10, "nini");
    }
}
