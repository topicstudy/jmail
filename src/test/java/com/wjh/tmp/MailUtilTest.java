package com.wjh.tmp;

import com.wjh.mail.entity.Mail;
import com.wjh.mail.entity.MailAddress;
import com.wjh.mail.entity.MailSender;
import com.wjh.mail.common.MailUtil;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailUtilTest {
    /**
     * 发送者
     */
    private static MailSender sender;
    private static Map<String, MailSender> senderMap = new HashMap();

    static {
        // sohu
        senderMap.put("sohu", new MailSender(new MailAddress("wjhshm@sohu.com", "搜狐测试"),
                "30RPATSOCLJ61"));

        sender = senderMap.get("sohu");
    }

    /**
     * 接收者
     */
    private static List<MailAddress> recipientList = new ArrayList();

    static {
        recipientList.add(new MailAddress("wjh3493@qq.com", "接收者2"));
        recipientList.add(new MailAddress("wjh@dareway.com.cn", "接收者4"));
    }

    @Test
    public void sendSimpleMail() throws Exception {
        MailUtil.send(getSimpleMail());
    }

    @Test
    public void sendCompleteMail() throws Exception {
        MailUtil.send(getCompleteMail());
    }

    private Mail getSimpleMail() {
        Mail mail = new Mail();
        mail.setSender(sender);
        mail.setRecipientList(recipientList);
        mail.setSubject("搜狐测试-简单邮件");
        mail.setHtml("【腾讯】您的验证码是1234，不可告诉任何人！");
        return mail;
    }

    private Mail getCompleteMail() throws Exception {
        Mail mail = new Mail();

        mail.setSender(sender);
        mail.setRecipientList(recipientList);
        mail.setSubject("搜狐测试-完整邮件");

        mail.setHtml("<h1>1a汉字</h1><img src='p1.png'><img src='p2.png'>");

        List<File> pics = new ArrayList();
        pics.add(new File("d:/tmp/mail/p1.png"));
        pics.add(new File("d:/tmp/mail/p2.png"));
        mail.setPicList(pics);

        List<File> attachments = new ArrayList();
        attachments.add(new File("d:/tmp/mail/a1.txt"));
        attachments.add(new File("d:/tmp/mail/a2.txt"));
        mail.setAttachmentList(attachments);

        return mail;
    }


}