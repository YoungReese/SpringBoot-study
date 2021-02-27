package com.ly.controller;

import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import com.ly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello() {
        return "ok";
    }

    @GetMapping("/u")
    public List<User> getUsers() {
        return userService.getUsers();
    }




    /******************以下代码不规范，仅用于在本项目中快速调试********************/
    /**
     * controller直接调用service层，仅仅用于测试
     */
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/q")
    public List<User> queryUsers() {
        return userMapper.queryUsers();
    }

}
