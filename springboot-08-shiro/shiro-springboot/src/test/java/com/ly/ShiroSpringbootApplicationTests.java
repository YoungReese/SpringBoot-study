package com.ly;

import com.ly.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetUserByName() {
        System.out.println(userService.getUserByName("liyang"));
    }
}
