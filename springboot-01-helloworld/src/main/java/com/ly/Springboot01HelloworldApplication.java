package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication : 标注这个类是一个Springboot的应用
@SpringBootApplication
public class Springboot01HelloworldApplication {
    public static void main(String[] args) {
        // 将springboot应用启动
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }
}
