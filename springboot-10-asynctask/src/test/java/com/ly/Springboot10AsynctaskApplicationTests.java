package com.ly;

import com.ly.constants.MailConstants;
import com.ly.utils.SendEmailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot10AsynctaskApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MailConstants.mailFrom);
        System.out.println(MailConstants.mailTo);
    }



    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 发送一个简单的邮件
     */
    @Test
    void testSendMail() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(MailConstants.mailFrom);
        mailMessage.setTo(MailConstants.mailTo);
        mailMessage.setSubject("学习使用springboot发送简单邮件01");
        mailMessage.setText("简单邮件 => hello world => 01");

        mailSender.send(mailMessage);
    }


    /**
     * 发送一个复杂的邮件
     */
    @Test
    void testSendComplexMail() throws MessagingException {
        /**
         * 创建MimeMessage mimeMessage = new MimeMessage();
         */
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        /**
         * 使用MimeMessageHelper帮助组装
         * multipart：多文件（非必需）
         * encoding：编码方式（非必需）
         */
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8"); // 重载
        /**
         * 地址
         */
        helper.setFrom(MailConstants.mailFrom);
        helper.setTo(MailConstants.mailTo);
        /**
         * 正文
         */
        helper.setSubject("学习使用springboot发送复杂邮件02");
        helper.setText("<p style='color:blue'>复杂邮件 => hello world => 02</p>", true); // html = true 表示以html方式解析text中的文本
        /**
         * 附件："File must not be null"
         */
        helper.addAttachment("car.png", new File("/Users/11117846/Desktop/current/car.png")); // 添加附件

        mailSender.send(mimeMessage);
    }


    /**
     * 测试封装复杂邮件的工具类
     */
    @Test
    void testSendComplexEmailByMyMailUtils() throws MessagingException {
        SendEmailUtils.sendEmail(
                "学习使用springboot发送复杂邮件03-使用自己封装的工具类",
                "<p style='color:green'>复杂邮件(使用自己封装的工具类) => hello world => 03</p>",
                true,
                "", new File("")
        );
    }


}
