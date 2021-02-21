package com.ly;

import redis.clients.jedis.Jedis;

/**
 * liyang 2021-02-21
 * Jedis的API测试
 */
public class TestJedisType {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());

        // String
        System.out.println(jedis.set("k1", "v1"));
        System.out.println(jedis.get("k1")); // v1
        System.out.println(jedis.append("k1", " + value"));
        System.out.println(jedis.get("k1")); // v1 + value
        System.out.println(jedis.strlen("k1")); // 10
        System.out.println("=====================================================");

        // List
        System.out.println(jedis.lpush("listKey", "l1", "l2", "l3"));
        System.out.println(jedis.lrange("listKey", 0, -1)); // [l3, l2, l1]
        System.out.println(jedis.llen("listKey"));
        System.out.println("=====================================================");

        // Hash
        System.out.println(jedis.hset("hashKey", "k1", "v1"));
        System.out.println(jedis.hset("hashKey", "k2", "v2"));
        System.out.println(jedis.hset("hashKey", "k3", "v3"));
        System.out.println(jedis.hmget("hashKey", "k1", "k2", "k3")); // [v1, v2, v3]
        System.out.println(jedis.hgetAll("hashKey")); // {k3=v3, k2=v2, k1=v1}
        System.out.println("=====================================================");

        // Set
        System.out.println(jedis.sadd("setKey", "s1", "s2", "s3", "s4"));
        System.out.println(jedis.smembers("setKey")); // [s2, s1, s4, s3]
        System.out.println(jedis.scard("setKey"));
        System.out.println("=====================================================");

        // ZSet
        System.out.println(jedis.zadd("ZKey", 90, "z1"));
        System.out.println(jedis.zadd("ZKey", 80, "z2"));
        System.out.println(jedis.zadd("ZKey", 85, "z3"));
        System.out.println(jedis.zrange("ZKey", 0, -1)); // [z2, z3, z1]
    }

}
