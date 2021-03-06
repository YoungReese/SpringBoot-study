package com.ly.utils;

import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AsyncCacheUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("cacheBean")
    ConcurrentHashMap<String, Object> cacheBean;

    @Autowired
    private UserMapper userMapper;

    @Async
    public void queryAllAndCacheTask() {
        List<User> users = userMapper.queryAll();
        cacheIntoConcurrentHashMap("list", users);
    }

    @Async
    public void cacheAllAsyncTask(List<User> users) {
        cacheIntoConcurrentHashMap("list", users);
    }

    @Async
    public void queryLatestAndCacheAsyncTask() {
        User user = userMapper.queryLatest();
        cacheIntoConcurrentHashMap("entity", user);
    }

    @Async
    public void cacheLatestAsyncTask(User user) {
        cacheIntoConcurrentHashMap("entity", user);
    }


    private void cacheIntoConcurrentHashMap(String type, Object cachedObject) {
        if ("list".equals(type)) {
            cacheListIntoConcurrentHashMap((List<User>) cachedObject);
        } else {
            cacheEntityIntoRedis((User) cachedObject);
        }
    }

    private void cacheListIntoConcurrentHashMap(List<User> users) {
        if (users == null || users.size() == 0) return;
        cacheBean.put("data", users);
        logger.info("Data is processed");
    }

    private void cacheEntityIntoRedis(User user) {
        if (user == null) return;
        cacheBean.put("last", user);
        logger.info("Data is processed");
    }


}
