package com.ly.controller;

import com.ly.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    AsyncService service;

    @GetMapping("/async")
    public String simpleAsyncTask() {
        service.simpleAsyncTask();
        return "ok!";
    }

}
