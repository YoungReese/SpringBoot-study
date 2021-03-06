package com.ly.controller;

import com.ly.pojo.User;
import com.ly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/")
    public String test() {
        return "It's okay!";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> queryAll() {
        return userService.queryAll();
    }

    @GetMapping("/last")
    public User queryLatest() {
        return userService.queryLatest();
    }

}
