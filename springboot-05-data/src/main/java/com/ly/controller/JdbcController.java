package com.ly.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询数据库的所有信息
    // 没有实体类的情况下，数据库中的信息怎么获取与接收？ 可以，使用万能map套路
    @GetMapping("/select")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @GetMapping("/add")
    public String addUser() {
        String sql = "insert into mybatis.user(id, name, pwd) values(8, 'sp-jack', 'sp-123456') ";
        jdbcTemplate.update(sql);
        return "addUser:ok!";
    }


    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id) {
        String sql = "update mybatis.user set name = ?, pwd = ? where id =" + id;

        Object[] objects = new Object[2];
        objects[0] = "springboot-jack";
        objects[1] = "springboot-123456";
        jdbcTemplate.update(sql, objects);
        return "updateUser:ok!";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql, id);
        return "delete:ok!";
    }



}
