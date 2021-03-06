package com.ly.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 异步任务
 */
@Service
public class AsyncService {

    /**
     * 异步任务可以手动开启一条子线程执行，但是在springboot中我么使用注解即可
     * @Async 会开启线程池来帮助我们执行异步任务
     */
    @Async
    public void simpleAsyncTask() {
        try { // 模拟一个延时操作
            System.out.println("Data is processing ...");
            TimeUnit.MILLISECONDS.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Data is processed");
    }

}
