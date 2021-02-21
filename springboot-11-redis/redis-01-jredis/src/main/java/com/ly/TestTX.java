package com.ly;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * liyang 2021-02-21
 * 测试事务
 */
public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "liyang");
        jsonObject.put("age", "18");
        jsonObject.put("gender", "boy");

        Transaction multi = jedis.multi(); //  开启事务
        String user = jsonObject.toJSONString();

        try {
            multi.set("user1", user);
            multi.set("user2", user);
//            int i = 1 / 0;  // 模拟事务出现异常
            multi.exec();
        } catch (Exception e) {
            multi.discard(); // 出现问题，放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.mget("user1", "user2"));
            jedis.close(); // 关闭连接
        }
    }
}
