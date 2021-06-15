package com.ly.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DistributedController {

    @GetMapping("/")
    public String hello() {
        return "hello, distribute02";
    }

    /**
     * 测试url传参数
     */
    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
                            @RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
        int a = x.intValue();
        int b = y.intValue();

        String res = "past";
        if (a + b > 2021) {
            res = "future";
        } else if (a + b == 2021) {
            res = "now";
        }

        return res;
    }
}
