package com.ly.controller;

import com.ly.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * liyang 2021-02-19
 * 一个项目即使不写任何controller，都会有一个默认的请求 /error
 */
@Controller
//@RestController 可以替代各方法上的 @ResponseBody
public class HelloController {

    @ApiOperation("HelloController.class=>hello方法")
    @GetMapping({"/", "/hello"})
    @ResponseBody
    public String hello() {
        return "hello, swagger";
    }

    /**
     * 只要我们的接口中，返回值存在实体类，它就会被扫描到swagger中
     */
    @ApiOperation("HelloController.class=>user方法")
    @GetMapping("/user")
    @ResponseBody
    public User user() {
        return new User("liyang", "12345678");
    }

    @ApiOperation("HelloController.class=>greet方法")
    @PostMapping("/greet")
    @ResponseBody
    public User greet(@ApiParam("用户名") User user) {
        return user;
    }

}
