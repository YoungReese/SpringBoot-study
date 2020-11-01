package com.ly.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {

        return new DruidDataSource();
    }

    // 后台监控功能，相当于替代了web.xml（注：有人说可以使用yaml文件配置，待验证！）
    // 因为Springboot内置了servlet容器，所以没有web.xml文件
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        // 后台需要有人登陆，账号密码
        HashMap<String, String> initParameters = new HashMap<>();
        // 增加配置，登陆的key在druid中是固定参数
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "12345678");

//        // 允许谁可以访问（value为空，则任何人都可以访问，localhost只有本机可以访问，指定名字表示只有某人可以访问）
//        initParameters.put("allow", "localhost");
//
//        // 禁止谁访问
//        initParameters.put("ly", "192.168.1.123");

        bean.setInitParameters(initParameters); // 设置初始化参数

        return bean;
    }

    // filter功能
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {

        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new WebStatFilter());

        // 可以过滤哪些请求
        HashMap<String, String> initParameters = new HashMap<>();
        // 这些信息排除在统计信息里面，也就是不进行统计
        initParameters.put("exclusions", "*.js, *.css, /druid/*");


        bean.setInitParameters(initParameters);
        return bean;
    }

}
