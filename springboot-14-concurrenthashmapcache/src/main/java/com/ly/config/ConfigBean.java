package com.ly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ConfigBean {

    @Bean(name = "cacheBean")
    public ConcurrentHashMap<String, Object> cacheBean() {
        return new ConcurrentHashMap<>();
    }

}
