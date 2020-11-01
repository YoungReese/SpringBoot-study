package com.ly.controller;

import com.ly.mapper.UserMapper;
import com.ly.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/query")
    public List<User> queryUsers() {
        List<User> users = userMapper.queryUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    @GetMapping("/query/{id}")
    public User queryUserById(@PathVariable("id") int id) {
        return userMapper.queryUserById(id);
    }

    @GetMapping("/add")
    public String addUser() {
        userMapper.addUser(new User(9, "helloworld", "123"));
        return "ok";
    }

    @GetMapping("/update")
    public String updateUser() {
        userMapper.updateUser(new User(9, "mybatis-sp", "456"));
        return "ok";
    }

    @GetMapping("/delete")
    public String deleteUser() {
        userMapper.deleteUser(9);
        return "ok";
    }


}
