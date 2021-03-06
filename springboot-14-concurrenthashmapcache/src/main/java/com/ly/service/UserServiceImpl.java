package com.ly.service;

import com.ly.dao.UserMapper;
import com.ly.pojo.User;
import com.ly.utils.AsyncCacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAll() {
//        return userMapper.queryAll();
        return getUsers();
    }

    @Override
    public User queryLatest() {
//        return userMapper.queryLatest();
        return getLatestUser();
    }





//    @Autowired
//    @Qualifier("cacheBean")
    @Resource(name = "cacheBean")
    ConcurrentHashMap<String, Object> cacheBean;


    @Autowired
    private AsyncCacheUtils asyncCacheTackUtils;

    public List<User> getUsers() {
        logger.info("getUsers");
        List<User> users;
        if (cacheBean.containsKey("data")) {
            logger.info("cache Bean has the key");
            users = (List<User>) cacheBean.get("data");
            asyncCacheTackUtils.queryAllAndCacheTask();
        } else {
            users = userMapper.queryAll();
            asyncCacheTackUtils.cacheAllAsyncTask(users);
        }
        System.out.println(users);
        return users;
    }


    public User getLatestUser() {
        logger.info("getLatestUser");
        User user;
        if (cacheBean.containsKey("last")) {
            logger.info("cache Bean has the key");
            user = (User) cacheBean.get("last");
            asyncCacheTackUtils.queryLatestAndCacheAsyncTask();
        } else {
            user = userMapper.queryLatest();
            asyncCacheTackUtils.cacheLatestAsyncTask(user);
        }
        System.out.println(user);
        return user;
    }


}
