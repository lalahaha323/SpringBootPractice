package com.example.springbootmybatistx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootmybatistx.dao")
public class SpringbootMybatisTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisTxApplication.class, args);
    }

}
