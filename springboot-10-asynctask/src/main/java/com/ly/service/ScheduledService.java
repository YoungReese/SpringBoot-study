package com.ly.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务
 *
 * 在线生成cron表达式：https://cron.qqe2.com/
 * 参考资料：https://www.jianshu.com/p/1defb0f22ed1
 */
@Service
public class ScheduledService {

    private static int i = 0;

    /**
     * cron表达式
     * 秒 分 时 日 月 周几
     *
     * 0 0 17 * * ?  =>  每天17点 执行一次
     * 0 0/5 10,18 * * ?  => 每天10点和18点，每隔五分钟执行一次
     */
    @Scheduled(cron="${time.cron}")
    public void simpleScheduledTask() {
        System.out.println("simpleScheduledTask => 被执行了！");
    }

    @Scheduled(cron="0/${time.interval} * * * * *")
    public void loopScheduledTask() {
        System.out.println("loopScheduledTask => 被执行" + (++i) +"次了！");
    }


}
