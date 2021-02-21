package com.ly.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * liyang 2021-02-21
 * 在企业中，我们的所有的pojo都会序列化！
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String name;
    private int age;
}
