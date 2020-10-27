package com.ly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;



/**
 * liyang 2020-10-27
 * 在了解MVC自动装配原理后，如果你想自定义一些功能，
 * 只要写完这个组件，然后将它交给你Springboot，他会帮我们自动装配！
 *
 * 扩展SpringMVC   dispatchServlet
 *
 * 因为类型要求为WebMvcConfigurer，所以我们实现其接口
 * 可以使用自定义类扩展MVC的功能
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送/ly ， 就会跳转到test页面；
        registry.addViewController("/ly").setViewName("test");
    }

    // 以下代码是上次测试代码，可以删除，我暂时放在这，因为不影响上面这个函数的测试！
    @Bean //放到bean中
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    //我们写一个静态内部类，视图解析器就需要实现ViewResolver接口
    private static class MyViewResolver implements ViewResolver{
        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }

}
