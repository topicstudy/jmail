package cn.topicstudy.mail.entity;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mail {
    // 发件人，必填
    private MailSender sender;

    // 收件人，必填
    private List<MailAddress> recipientList;
    private Address[] recipientAddresses;// 内容和recipientList一样,调用者只用设置recipientList

    // 主题，必填
    private String subject;

    // 超文本，必填
    private String html;

    // 内嵌图片，即内容中的图片
    private List<File> picList = new ArrayList();// 非必填项需要初始化，否则遍历时NPE

    // 附件
    private List<File> attachmentList = new ArrayList();


    public MailSender getSender() {
        return sender;
    }

    public void setSender(MailSender sender) {
        this.sender = sender;
    }

    public List<MailAddress> getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(List<MailAddress> recipientList) {
        this.recipientList = recipientList;

        // 将recipientList的内容复制到recipientAddresses
        this.recipientAddresses = new InternetAddress[recipientList.size()];
        for (int i = 0; i < recipientList.size(); i++) {
            MailAddress mailAddress = recipientList.get(i);
            try {
                recipientAddresses[i] = new InternetAddress(mailAddress.getAddressWithNickname());
            } catch (AddressException e) {
                e.printStackTrace();
            }
        }
    }

    public Address[] getRecipientAddresses() {
        return this.recipientAddresses;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<File> getPicList() {
        return picList;
    }

    public void setPicList(List<File> picList) {
        this.picList = picList;
    }

    public List<File> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<File> attachmentList) {
        this.attachmentList = attachmentList;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "sender='" + sender + '\'' +
                ", recipientList=" + recipientList +
                ", subject='" + subject + '\'' +
                ", html='" + html + '\'' +
                ", picList=" + picList +
                ", attachmentList=" + attachmentList +
                '}';
    }
}
