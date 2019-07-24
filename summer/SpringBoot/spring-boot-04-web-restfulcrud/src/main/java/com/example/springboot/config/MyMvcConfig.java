package com.example.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;



//使用WebMvcConfigurer扩展SpringMvc使用的功能
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        //浏览器发送 /lala 请求来到success页面
        registry.addViewController("/lala").setViewName("success");
    }
}
