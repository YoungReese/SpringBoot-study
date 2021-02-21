package com.ly;

import redis.clients.jedis.Jedis;

/**
 * liyang 2021-02-21
 * 测试是否连接上了redis
 */
public class TestPing {

    public static void main(String[] args) {
        /**
         * new一个redis对象
         */
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        /**
         * 测试连通性
         * 成功会打印PONG
         */
        System.out.println(jedis.ping());

        /**
         * 之前的命令都可以通过redis对象进行调用
         */
        jedis.set("k", "v");
        System.out.println(jedis.get("k"));
    }

}
