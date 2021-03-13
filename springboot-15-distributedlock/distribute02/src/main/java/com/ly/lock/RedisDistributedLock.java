package com.ly.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.ly.constant.RedisKeyConstant.REDIS_DISTRIBUTE_LOCK_KEY;

@Component
public class RedisDistributedLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean checkWhetherCanExecuteOrNot() {
        /**
         * 如果key不存在，则第一个建立key的执行
         */
        if(stringRedisTemplate.opsForValue().setIfAbsent(REDIS_DISTRIBUTE_LOCK_KEY, "1")){
            // 对应setnx命令，可以成功设置,也就是key不存在
            stringRedisTemplate.expire(REDIS_DISTRIBUTE_LOCK_KEY, Duration.ofSeconds(60)); // 60秒
            return true;
        } else {
            stringRedisTemplate.expire(REDIS_DISTRIBUTE_LOCK_KEY, Duration.ofSeconds(60)); // 60秒
            return false;
        }
    }

}
