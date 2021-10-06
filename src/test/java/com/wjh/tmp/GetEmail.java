package com.wjh.tmp;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * 收邮件
 */
public class GetEmail {
    public static void main(String[] args) throws Exception {
        Session session = Session.getInstance(new Properties());
        Store store = session.getStore("pop3");
        store.connect("pop3.sohu.com", 110, "wjhshm", "30RPATSOCLJ61");
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);

        // 所有邮件数量
        int allCount = folder.getMessageCount();
        // 未读邮件数量
        int unreadMessageCount = folder.getUnreadMessageCount();
        // 已删邮件数量
        int deletedMessageCount = folder.getDeletedMessageCount();

        System.out.println("allCount=" + allCount
                + ",unreadMessageCount=" + unreadMessageCount
                + ",deletedMessageCount=" + deletedMessageCount
        );

        for (int i = 0; i < allCount; i++) {
            Message mail = folder.getMessage(i + 1);
            parseMail(mail);
        }
    }

    static void parseMail(Message mail) throws Exception {
        System.out.println("start=========================");
        /*
         * 头
         */
        Address[] from = mail.getFrom();
        System.out.println("from=" + Arrays.toString(from));

        Date sentDate = mail.getSentDate();
        System.out.println("sentDate=" + sentDate);

        Address[] recipients = mail.getRecipients(Message.RecipientType.TO);
        System.out.println("recipients=" + Arrays.toString(recipients));

        Date receivedDate = mail.getReceivedDate();
        System.out.println("receivedDate=" + receivedDate);

        String subject = mail.getSubject();
        System.out.println("subject=" + subject);

        /*
         * 体body=正文content+附件attachment
         * 所有种类邮件的：content类型都是MimeMultipart
         *                content.getCount()都是2
         *
         *
         */
        MimeMultipart body = (MimeMultipart) mail.getContent();
        BodyPart b0 = body.getBodyPart(0);
        BodyPart b1 = body.getBodyPart(1);
        System.out.println("0:contentType=" + b0.getContentType() + ",content=" + b0.getContent());
        System.out.println("1:contentType=" + b1.getContentType() + ",content=" + b1.getContent());

        System.out.println("end===========================");
    }

    static void parseMailContent(Message mail) throws Exception {
        MimeMultipart body = (MimeMultipart) mail.getContent();
        BodyPart b0 = body.getBodyPart(0);
        BodyPart b1 = body.getBodyPart(1);
        String contentType0 = b0.getContentType();
        String contentType1 = b1.getContentType();
        contentType0 = contentType0.substring(0, contentType0.indexOf(";"));
        contentType1 = contentType1.substring(0, contentType1.indexOf(";"));
        if ("text/plain".equals(contentType0) && "text/html".equals(contentType1)) {
            System.out.println("邮件类型=【无附件 无内嵌图片】，邮件内容=" + b1.getContent());
        } else if ("multipart/related".equals(contentType0) && "application/octet-stream".equals(contentType1)) {
            System.out.println("邮件类型=【有附件 有内嵌图片】，邮件内容=");
            // 附件
            // 内嵌图片
            // html
        }
    }
}
