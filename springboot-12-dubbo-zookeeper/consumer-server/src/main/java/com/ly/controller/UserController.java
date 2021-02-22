package com.ly.controller;

import com.ly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getTicketByDubboInThisUser() {
        return userService.buyTicket();
    }
}
