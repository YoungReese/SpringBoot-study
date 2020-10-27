package com.ly.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * liyang 2020-10-27
 * 员工表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String name;
    private String email;
    private Integer gender; // 0: female  1:male

    private Department department;
    private Date birth;

}
