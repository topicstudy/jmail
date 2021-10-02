package com.wjh.tmp;

import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileOutputStream;
import java.util.Properties;

public class T1 {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        //properties.setProperty("mail.smtp.auth", "true");
        //properties.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom("wjhshm@sohu.com");
        message.setSubject("邮件主题");
        message.setText("邮件内容-中午12.30再3楼小会议室开会,请带上笔记本等");
        message.setSender(new InternetAddress("wwww@alibaba.com"));


        Transport transport = session.getTransport();
        transport.connect("smtp.sohu.com", 25, "wjhshm", "I4NEVJAC2BO1X8R");
        transport.sendMessage(message, new Address[]{
                new InternetAddress("wjh.q@qq.com"),
                new InternetAddress("wjh2.q@qq.com"),
        });

        transport.close();
    }

    /**
     * 邮件 = 头+内容(正文+附件)
     */
    @Test
    public void createMail() throws Exception {
        Session session = Session.getInstance(new Properties());
        MimeMessage message = new MimeMessage(session);
        // 邮件-头（信封）
       // message.setSender(new InternetAddress("wjhshm@sohu.com"));
        message.setFrom(new InternetAddress("吴建豪搜狐<wjhshm@sohu.com>"));
        message.setRecipients(Message.RecipientType.TO, "wjh.q@qq.com");
        message.setSubject("主题-开会");
        //message.setReplyTo();

        Multipart content = new MimeMultipart("mixed");
        message.setContent(content);

        // 内容content=正文body+附件attachment
        // body
        MimeBodyPart body = new MimeBodyPart();
        // attachment
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.setDataHandler(new DataHandler(new FileDataSource("d:/tmp/mail/a1.txt")));
        attachment.setFileName("a1方法.txt");

        content.addBodyPart(body);
        content.addBodyPart(attachment);

        // body
        MimeMultipart bodyMultiPart = new MimeMultipart("related");
        body.setContent(bodyMultiPart);

        MimeBodyPart html = new MimeBodyPart();
        MimeBodyPart pic = new MimeBodyPart();

        pic.setDataHandler(new DataHandler(new FileDataSource("d:/tmp/mail/p1.png")));
        pic.setHeader("Content-Location", "p1.png");
        html.setContent("<h1>11</h1><img src='p1.png'>", "text/html;charset=utf-8");

        bodyMultiPart.addBodyPart(html);
        bodyMultiPart.addBodyPart(pic);

        message.saveChanges();

        saveMail(message);
    }


    void saveMail(Message message) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("d:/tmp/mail/m.eml");
        message.writeTo(fileOutputStream);
    }
}
