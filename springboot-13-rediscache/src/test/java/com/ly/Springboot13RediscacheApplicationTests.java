package com.ly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ly.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Springboot13RediscacheApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    @Qualifier("myStringRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void testRedis() throws JsonProcessingException {

        User user = new User(1, "liyang");

        // 真实的开发一般都使用json来传递对象
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", jsonUser);
        System.out.println(redisTemplate.opsForValue().get("user"));

        /**
         * todo：存在一个疑问
         * 直接使用对象，这时候实体类需要实现序列化，这里第一次使用序列化成功了，后面报错，然后吧user改成user.toString()成功
         */
        redisTemplate.opsForValue().set("user02", user.toString());
        System.out.println(redisTemplate.opsForValue().get("user02"));
    }


}
