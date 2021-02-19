package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2  // 如果放在配置出现问题，可以在主启动类上加上，有网友遇到过这种问题
public class Springboot09SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot09SwaggerApplication.class, args);
    }

}
