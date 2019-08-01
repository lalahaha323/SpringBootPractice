package com.example.springbootmybatistx;

import com.example.springbootmybatistx.service.OrdersService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {

    @Test
    public void testAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        OrdersService userService = (OrdersService) context.getBean("ordersService");
        userService.accountMoney();
    }
}
