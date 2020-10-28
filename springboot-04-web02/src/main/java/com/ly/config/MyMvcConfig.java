package com.ly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * liyang 2020-10-28
 *
 * 可以直接使用controller方式实现页面跳转
 * 不过，建议使用扩展mvc的方式，也就是这里使用的方式！
 *
 * 为了测试该方法是否生效，将IndexController给注释了
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");

    }

    // 自定义的国际化组件在这里注册Bean后就生效了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
