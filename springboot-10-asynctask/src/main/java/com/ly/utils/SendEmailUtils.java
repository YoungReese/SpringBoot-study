package com.ly.utils;

import com.ly.constants.MailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * liyang 2021-02-20
 * 封装邮件发送的工具类
 * 默认支持multipart，默认编码方式utf-8，硬编码，可根据实际情况修改
 *
 *
 * 针对静态函数如何使用注入的bean：
 *
 * 方法2：
 * private static JavaMailSenderImpl mailSender;
 *
 * public SendEmailUtils(JavaMailSenderImpl sender) {
 *      mailSender = sender;
 * }
 *
 * 参考资料：
 * https://blog.csdn.net/hzr0523/article/details/81533685
 * https://www.jianshu.com/p/98cf7d8b9ec3
 */
@Component
public class SendEmailUtils {

    /********************************************************************************************/
    /**
     * 方法1：
     * Constructor >> @Autowired >> @PostConstruct
     *
     * 在springframework里，我们不能@Autowire一个静态变量，使之成为一个spring bean
     * 因此sender不能被static修饰，从未无法被工具类的静态函数直接使用，所以使用一个静态变量中转一下
     */
    @Autowired
    JavaMailSenderImpl sender;
    /**
     * 静态中转变量
     */
    private static JavaMailSenderImpl mailSender;
    /**
     * 因为Autowired会在构造函数之后注入，因此使用构造函数会使得mailSender为null，不注意的话就会产生空指针异常
     * @PostConstruct 注解的方法将会在依赖注入完成后被自动调用，从而保证mailSender不为空
     */
    @PostConstruct
    public void init() {
        mailSender = sender;
    }
    /********************************************************************************************/

    public static void sendEmail(String subject, String text, boolean html, String attachmentFileName, File file) throws MessagingException {

        if (mailSender == null) {
            System.out.println("mailSender is null");
            return;
        }

        /**
         * 创建MimeMessage mimeMessage = new MimeMessage();
         */
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        /**
         * 使用MimeMessageHelper帮助组装（由多个重载函数）
         * multipart：多文件（非必需）
         * encoding：编码方式（非必需）
         */
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        /**
         * 地址
         */
        helper.setFrom(MailConstants.mailFrom);
        helper.setTo(MailConstants.mailTo);

        /**
         * 正文
         * html = true 表示以html方式解析text中的文本
         */
        helper.setSubject(subject);
        helper.setText(text, html);

        /**
         * 附件："File must not be null"
         */
        if (attachmentFileName != "" && file != null) {
            helper.addAttachment(attachmentFileName, file);
        }


        mailSender.send(mimeMessage);
    }

}
