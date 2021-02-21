package com.ly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * liyang 2021-02-21
 * redis相关操作需要序列化，否则显示会出现问题
 * 这里单测显示 hello, redis
 * 但是redis客户端存储显示的是 "\xac\xed\x00\x05t\x00\x05hello"
 *
 *
 * 备注：从SpringBoot2.x之后，原先使用的Jedis被lettuce替代
 * Jedis：采用直连，模拟多个线程操作会出现安全问题。为避免此问题，需要使用Jedis Pool连接池！类似于BIO模式
 * lettuce：采用netty网络框架，对象可以在多个线程中被共享，完美避免线程安全问题，减少线程数据，类似于NIO模式
 *
 * 参考资料：
 * https://blog.csdn.net/weixin_46468474/article/details/109807495
 */
@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        /**
         * 获取redis的连接对象
         */
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        connection.flushAll();

        /**
         * redisTemplate
         * opsForValue  操作字符串 类似String
         * opsForList   操作List 类似List
         * opsForSet    ...
         * opsForHash
         * opsForZSet
         * opsForGeo
         * opsForHyperLogLog
         */
        redisTemplate.opsForValue().set("hello", "hello, redis");

        System.out.println(redisTemplate.opsForValue().get("hello"));

    }

}
