package com.ly;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * liyang 20210-2-21
 */
public class TestKey {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        System.out.println("清空当前数据库数据：" + jedis.flushDB());
        System.out.println("判断键username是否存在：" + jedis.exists("username"));
        System.out.println("新增<username, liyang>键值对：" + jedis.set("username", "liyang"));
        System.out.println("新增<password, 12345678>键值对：" + jedis.set("password", "12345678"));
        System.out.println("系统中所有键如下：");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("删除键password：" + jedis.del("password"));
        System.out.println("判断键password是否存在：" + jedis.exists("password"));
        System.out.println("查看键username所存储的值的类型：" + jedis.type("username"));
        System.out.println("随键返回key空间的一个：" + jedis.randomKey());
        System.out.println("重命名key：" + jedis.rename("username", "name"));
        System.out.println("取出重命名后的key：" + jedis.get("name"));
        System.out.println("按索引查询：" + jedis.select(0));
        System.out.println("清空当前数据库数据：" + jedis.flushDB());
        System.out.println("查看当前数据库中key的数目：" + jedis.dbSize());
        System.out.println("清空所有数据库数据：" + jedis.flushAll());

    }
}
