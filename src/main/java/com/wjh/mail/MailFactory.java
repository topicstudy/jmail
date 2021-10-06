package com.wjh.mail;

import com.wjh.mail.entity.Mail;
import com.wjh.mail.entity.MailSender;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.List;

/**
 * 创建邮件
 */
public class MailFactory {
    /**
     * 创建邮件
     * 邮件 = 头 + 体，体 = 正文 + 附件
     */
    public Message createEmail(Mail email, Session session) throws Exception {
        MimeMessage mail = new MimeMessage(session);
        MailSender sender = email.getSender();


        /*
         * 头head
         * setFrom和setRecipients的作用是在邮件头中展示发件人和收件人，不一定是真正的发件人和收件人
         */
        mail.setFrom(sender.getMailAddress().getAddressWithNickname());// setSender()设置的是代理发送者
        mail.setRecipients(Message.RecipientType.TO, email.getRecipientAddresses());
        mail.setSubject(email.getSubject());


        /*
         * 体body(正文content + 附件attachment)
         */
        MimeMultipart body = new MimeMultipart("mixed");

        // 正文content(超文本html + 内嵌图片pic)
        MimeBodyPart content = createMailContent(email);
        body.addBodyPart(content);

        // 附件attachment
        List<File> attachmentList = email.getAttachmentList();
        for (File a : attachmentList) {// 不用判断NPE，因为取到的AttachmentList一定非null
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setFileName(a.getName());// 不setFileName则看不到附件
            attachment.setDataHandler(new DataHandler(new FileDataSource(a)));
            body.addBodyPart(attachment);
        }

        mail.setContent(body);
        return mail;
    }

    /**
     * 创建正文content(超文本html + 内嵌图片pic)
     */
    private MimeBodyPart createMailContent(Mail mail) throws Exception {
        MimeMultipart content = new MimeMultipart("related");

        // 超文本html
        MimeBodyPart html = new MimeBodyPart();
        html.setContent(mail.getHtml(), "text/html;charset=UTF-8");
        content.addBodyPart(html);

        // 内嵌图片pic
        List<File> picList = mail.getPicList();
        for (File p : picList) {
            MimeBodyPart pic = new MimeBodyPart();
            pic.setDataHandler(new DataHandler(new FileDataSource(p)));
            pic.setHeader("Content-Location", p.getName());// 将pic与html中<img src="xx.png">关联起来
            content.addBodyPart(pic);
        }

        // multiPart => bodyPart
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content);
        return mimeBodyPart;
    }

}
