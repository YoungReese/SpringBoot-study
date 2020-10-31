package com.ly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot05DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        // 查看一下默认的数据源：class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());

        // 获得数据库连接：HikariProxyConnection@1171672359 wrapping com.mysql.cj.jdbc.ConnectionImpl@34fe326d
        Connection connection = dataSource.getConnection();
        System.out.println(connection);


        // xxxx Template : springboot已经配置好的模板bean，拿来即用
        // jdbc Template
        // redis Template

        connection.close();

    }

}
