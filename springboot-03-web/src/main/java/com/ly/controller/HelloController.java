package com.ly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * liyang 2020-10-26
 * 测试一下这个web项目是否能正常启动
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world and hello springboot!";
    }
}
