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
            /**
             * 如果一台及其执行完 setIfAbsent 就宕机，导致这个与之关联的定时任务没有执行且key也不会被设置 expire time，
             * 第2天同一个时刻 redis 查询会返回false，实际情况是这个正常工作了机器可以执行定时任务，怎么解决？
             *
             * 方案1（这里采用的方案）：所以这里重复设置一次expire time，保证单机器在当天之后依旧可用
             *
             * 方案2：新启动一个全局任务，固定时间清除这个 key
             *
             * 方案3：通过配置设定 redis 中 key 的失效时间
             */
            stringRedisTemplate.expire(REDIS_DISTRIBUTE_LOCK_KEY, Duration.ofSeconds(60)); // 60秒
            return false;
        }
    }
}
