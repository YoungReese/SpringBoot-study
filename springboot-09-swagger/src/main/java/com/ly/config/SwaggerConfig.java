package com.ly.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * liyang 2021-02-19
 * 对swagger的相关配置，如果类中为空，表示都是用默认配置
 */
@Configuration   // 配置到springboot
@EnableSwagger2  // 开启swagger2后可以访问http://localhost:8080/swagger-ui.html
public class SwaggerConfig {

    /**
     * 配置swagger实例bean
     * 配置多个Docket实例可以生成多个groupName，多人协同时，每个人负责自己的groupName下的文件
     */
    @Bean
    public Docket baseDocket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("base-all");
    }

    @Bean
    public Docket docket(Environment environment) {
        /**
         * 设置要返回swagger的环境
         */
        Profiles profiles = Profiles.of("dev", "test");
        /**
         * 判断当前运行环境是否是需要swagger
         */
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("springboot-09-swagger-ly")
                .enable(flag) // 关掉swagger后访问会显示 => 😱 Could not render e, see the console.
                .select()
                /**
                 * RequestHandlerSelectors，配置要扫描接口的方式
                 * basePackage：指定要扫描的包（推荐）
                 * any()：扫描全部
                 * none()：不扫描
                 * withClassAnnotation(xxx)：扫描类上有xxx注解的
                 * withMethodAnnotation(xxx)：扫描方法上有xxx注解的
                 */
                .apis(RequestHandlerSelectors.basePackage("com.ly.controller"))
                /**
                 * paths(xxx)：过滤xxx路径
                 */
//                .paths(PathSelectors.ant("/ly/**"))
                .build();
    }


    /**
     * 配置swagger信息，需要一个ApiInfo对象默认信息
     */
    private ApiInfo apiInfo() {

        // 作者信息
        final Contact contact = new Contact("liyang", "https://github.com/YoungReese", "12306@email.com");

        return new ApiInfo(
                "liyang的swagger练习作业",
                "随便改改swagger",
                "v1.0",
                "https://github.com/YoungReese", contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList()
        );
    }


}
