package com.ly.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * liyang 2021-02-22
 * 消费服务器，需要去生产者服务器获取服务
 *
 *
 * 方法1：使用@DubboReference，在远程服务相同的包路径下新建一个同名接口
 *
 *
 * 方法2：
 *
 */

@Service
public class UserService {
    /**
     * 需要接收provider-server提供的订票服务
     * 去注册中心拿到相关服务
     *
     * 方法1：使用@DubboReference，在远程服务相同的包路径下新建一个同名接口
     */
    @DubboReference
    TicketService ticketService;

    public String buyTicket() {
        String ticket = ticketService.getTicket();
        return "在注册中心拿到：" + ticket;
    }




}