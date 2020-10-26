package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication
public class Springboot03WebApplication {

    public static void main(String[] args) {

        SpringApplication.run(Springboot03WebApplication.class, args);

//        new WebMvcAutoConfiguration(); // 这里new这个对象仅仅是为了看他的源码，无其他作用
    }


}
