package com.ly.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * dubbo服务注册到注册中心需要使用dubbo的@Service
 * 容易与spring的@Service冲突，所以使用@DubboService
 */
@Service
@DubboService
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "游乐园门票";
    }
}
