package com.ly.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @GetMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello shiro!");
        return "index";
    }

    @GetMapping({"/user/add"})
    public String toAdd() {
        return "user/add";
    }

    @GetMapping({"/user/update"})
    public String toUpdate() {
        return "user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/login")
    public String toLogin(String username, String password, Model model) {

        /**
         * 获取当前的用户对象Subject
         */
        Subject currentUser = SecurityUtils.getSubject();

        /**
         * 封装当前用户的登陆数据
         * Token：令牌，通过用户名和密码生成
         */
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        /**
         * 使用令牌执行登录方法，没有异常表示登录成功
         */
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (AuthenticationException ae) {
            model.addAttribute("msg", "未知错误");
            return "login";
        }

        return "index";
    }

    @GetMapping("/noauth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权，无法访问此页面";
    }

}
