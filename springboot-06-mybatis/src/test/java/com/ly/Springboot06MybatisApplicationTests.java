package com.ly;

import com.ly.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
@ContextConfiguration()
class Springboot06MybatisApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }


    @Autowired
    UserMapper userMapper;

    @Test
    void testQueryUsers() {
        System.out.println(userMapper.queryUsers());
    }

}
