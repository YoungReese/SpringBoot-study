package com.ly.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器，防止未登陆，直接进入到后台
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 登陆成功之后应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null) { // 没有登陆
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false; // 不放行
        }

        return true; // 放行
    }
}
