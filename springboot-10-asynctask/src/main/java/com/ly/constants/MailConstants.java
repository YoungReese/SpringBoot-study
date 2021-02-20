package com.ly.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中的值到变量中
 * 由于使用静态变量接收，所以需要中转一下
 */
@Component
public class MailConstants {

    public static String mailFrom;
    public static String mailTo;

    @Value("${spring.mail.username}")
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    @Value("${demo.mailTo}")
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

}
