package com.example.mall2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mall2.mapper")
@SpringBootApplication
public class Mall2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mall2Application.class, args);
    }

}
