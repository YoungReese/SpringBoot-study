package com.ly.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Data
@Component // 注册bean
@ConfigurationProperties(prefix = "student")
@Validated // 数据校验
public class Student {
    private String firstName;
    private String lastName;
    @Email(message="邮箱格式错误") //name必须是邮箱格式
    private String email;
}
