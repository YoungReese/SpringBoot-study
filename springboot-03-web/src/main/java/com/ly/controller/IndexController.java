package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * liyang 2020-10-26
 * 页面跳转
 * 在template下的所有页面，只有通过controller来跳转，需要模版引擎（thymeleaf）的支持！
 *
 * 在pom文件中导入依赖
 *
 * template中新建test.html
 *
 */

@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model) {
        model.addAttribute("msg","Hello Thymeleaf");
        return "test";
    }

}