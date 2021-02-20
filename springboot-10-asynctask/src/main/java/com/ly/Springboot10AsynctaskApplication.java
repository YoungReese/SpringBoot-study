package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync       // 开启异步注解功能
@EnableScheduling  // 开启定时任务功能
public class Springboot10AsynctaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot10AsynctaskApplication.class, args);
    }

}
