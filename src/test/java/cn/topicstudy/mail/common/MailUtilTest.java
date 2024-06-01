package cn.topicstudy.mail.common;

import cn.topicstudy.mail.MailUtil;
import cn.topicstudy.mail.common.enums.SmtpServerEnum;
import cn.topicstudy.mail.entity.Mail;
import cn.topicstudy.mail.entity.MailAddress;
import cn.topicstudy.mail.entity.MailSender;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MailUtilTest {
    private String mailStr = "song@qq.com";
    private String notMailStr = "xyz";
    // 发件人
    private static MailSender sender = new MailSender();
    // 收件人
    private static List<MailAddress> recipientList = new ArrayList();


//    @Before
    public void setUp() throws Exception {
        sender.setMailAddress(new MailAddress("topicstudy@163.com", "发送者NICK"));//TODO
        sender.setPwd("ABCDEFG");//TODO
        sender.setSmtp(SmtpServerEnum.NETEASE.getServerAddress());
        sender.setSmtpPort(SmtpServerEnum.NETEASE.getServerPort());

        recipientList.add(new MailAddress("ccc@qq.com", "接收者123"));// TODO
        recipientList.add(new MailAddress("ddd@163.com", "接收者163"));//TODO
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsMail() {
        Assert.assertTrue(MailUtil.isMail(mailStr));
        Assert.assertFalse(MailUtil.isMail(notMailStr));
    }

    public void testSaveEmail() {
    }

     @Test
    public void testSend() throws Exception {
        MailUtil.send(createSimpleMail());
    }

     @Test
    public void testSend2() throws Exception {
         MailUtil.send(createCompleteMail());
    }

    /**
     * 创建简单邮件，即没有内嵌图片和附件
     */
    private Mail createSimpleMail() {
        Mail mail = new Mail();
        mail.setSender(sender);
        mail.setRecipientList(recipientList);
        mail.setSubject("搜狐测试主题-简单邮件");
        mail.setHtml("【新浪】验证码是1234，不可告诉任何人！");
        return mail;
    }

    /**
     * 创建完整邮件
     */
    private Mail createCompleteMail() {
        Mail mail = new Mail();
        String pathPrefix = "src/test/resources/";

        mail.setSender(sender);
        mail.setRecipientList(recipientList);
        mail.setSubject("搜狐测试主题-完整邮件");

        mail.setHtml("<h1>1a汉字</h1><img src='p1.png'><img src='p2.png'>");

        // 内嵌图片
        List<File> pics = new ArrayList();
        pics.add(new File(pathPrefix + "p1.png"));
        pics.add(new File(pathPrefix + "p2.png"));
        mail.setPicList(pics);

        // 附件
        List<File> attachments = new ArrayList();
        attachments.add(new File(pathPrefix + "a1.txt"));
        attachments.add(new File(pathPrefix + "a2.txt"));
        mail.setAttachmentList(attachments);

        return mail;
    }
}
