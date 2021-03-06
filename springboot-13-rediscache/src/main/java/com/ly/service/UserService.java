package com.ly.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * liyang 2021-02-27
 * <p>
 * 查询步骤：
 * 1、在redis中查找到直接返回，随后开启异步任务更新redis缓存
 * 2、在redis中没有找到，直接从数据库中查找，查找完并异步更新缓存
 */
@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("myStringRedisTemplate")
    private RedisTemplate redisTemplate;


    public List<User> getUsers() {
        logger.info("getUsers");
        List<User> users;
        if (redisTemplate.hasKey("data")) {
            logger.info("redis has key");
            String jsonUser = (String) redisTemplate.opsForValue().get("data");
            try {
                users = new ObjectMapper().readValue(jsonUser,new TypeReference<List<User>>(){});
                queryAndCacheIntoRedisAsyncTask(); // 异步查询和更新redis
            } catch (JsonProcessingException e) {
                logger.info("json解析出错");
                users = userMapper.queryUsers();   // 同步查询
                cacheIntoRedisAsyncTask(users);    // 异步更新Redis
                e.printStackTrace();
            }
        } else {
            users = userMapper.queryUsers();
            cacheIntoRedisAsyncTask(users);        // 异步更新redis
        }
        System.out.println(users);
        return users;
    }

    @Async
    void queryAndCacheIntoRedisAsyncTask() {
        List<User> users = userMapper.queryUsers();
        cacheIntoRedis(users);
    }

    @Async
    void cacheIntoRedisAsyncTask(List<User> users) {
        cacheIntoRedis(users);
    }

    private void cacheIntoRedis(List<User> users) {
        if (users == null || users.size() == 0) return;
        String jsonUsers = null;
        try {
            jsonUsers = new ObjectMapper().writeValueAsString(users);
        } catch (JsonProcessingException e) {
            logger.info("转换成json字符串出错！");
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set("data", jsonUsers);
        logger.info("Data is processed");
    }

}
