package com.ly;

import com.ly.lock.RedisDistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class Distribute01Application {

    public static void main(String[] args) {
        SpringApplication.run(Distribute01Application.class, args);
    }

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @Scheduled(cron = "0 0 17 ? * MON-SAT")
    public String scheduledNoticeTask01() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");     // a为am/pm的标记

        if (!redisDistributedLock.checkWhetherCanExecuteOrNot()) {
            System.out.println("现在时间：" + sdf.format(new Date()) + " cannot execute scheduledNoticeTask01!");
            return "cannot execute scheduledNoticeTask01";
        }

        System.out.println("现在时间：" + sdf.format(new Date()) + " execute => scheduledNoticeTask01");
        return "executed scheduledNoticeTask01";
    }

}
