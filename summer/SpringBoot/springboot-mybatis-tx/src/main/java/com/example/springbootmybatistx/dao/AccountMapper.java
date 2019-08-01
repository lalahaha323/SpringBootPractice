package com.example.springbootmybatistx.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@Repository
public interface AccountMapper {
    int update(@Param("money") double money, @Param("id") int id);
}
