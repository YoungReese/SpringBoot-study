package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync       // 开启异步注解功能
public class Springboot14ConcurrenthashmapcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot14ConcurrenthashmapcacheApplication.class, args);
    }

}
