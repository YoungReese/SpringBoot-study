package com.ly.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    // 解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 获取请求中的语言参数
        String language = httpServletRequest.getParameter("lan");
        // 如果请求的链接携带了国际化的参数，使用链接中携带的
        if (!StringUtils.isEmpty(language)) {
            String[] s = language.split("_"); // zh_CN
            return new Locale(s[0], s[1]); // 语言 国家
        }
        return Locale.getDefault(); // 如果没有则使用默认的
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
