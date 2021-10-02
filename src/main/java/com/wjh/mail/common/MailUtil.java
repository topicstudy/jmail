package com.wjh.mail.common;

import com.wjh.basic.text.StringUtil;
import com.wjh.mail.MailFactory;
import com.wjh.mail.entity.Mail;
import com.wjh.mail.entity.MailSender;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.io.FileOutputStream;
import java.util.Properties;

public class MailUtil {
    /**
     * 判断字符串是否是邮箱地址
     */
    public static boolean isMail(String s) {
        if (StringUtil.isBlank(s)) return false;
        if (!s.contains("@")) return false;
        return true;
    }

    /**
     * 将邮件保存到本地
     */
    public static void saveEmail(Message mail, String absolutePath) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(absolutePath);
        mail.writeTo(fileOutputStream);
        fileOutputStream.close();
    }

    /**
     * 发邮件
     */
    public static void send(Mail mail) throws Exception {
        // 创建邮件
        MailFactory mailFactory = new MailFactory();
        Session session = Session.getInstance(new Properties());
        session.setDebug(MailConstant.DEBUG);
        Message message = mailFactory.createEmail(mail, session);
        if (MailConstant.DEBUG) {
            MailUtil.saveEmail(message, MailConstant.SAVE_MAIL_ABSOLUTE_PATH);
        }

        // 发邮件
        Transport transport = session.getTransport();
        MailSender sender = mail.getSender();
        transport.connect(
                sender.getSmtp(),
                sender.getSmtpPort(),
                sender.getMailAddress().getName(),// 例如：zxj@qq.com中的zxj
                sender.getPwd()
        );// 邮件客户端连smtp服务器
        transport.sendMessage(message, mail.getRecipientAddresses());
        transport.close();
    }
}