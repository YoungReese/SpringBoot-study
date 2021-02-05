package com.ly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ly.dao")
public class Springboot00MybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot00MybatisplusApplication.class, args);
    }

}
