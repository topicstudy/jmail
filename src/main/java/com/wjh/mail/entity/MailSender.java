package com.wjh.mail.entity;

import com.wjh.mail.common.MailConstant;

/**
 * 发件人
 */
public class MailSender {
    // 发件人邮箱地址
    private MailAddress mailAddress;

    // 发件人邮箱密码、授权码
    private String pwd;

    // 发件人邮箱的SMTP服务器地址，例如：smtp.sohu.com
    private String smtp;

    // 发件人邮箱的SMTP服务器端口，默认25
    private Integer smtpPort = 25;

    public MailSender(MailAddress mailAddress, String pwd){
        this.mailAddress = mailAddress;
        this.pwd = pwd;

        String company = mailAddress.getEmailCompany();
        this.smtp = MailConstant.SMTP_MAP.get(company);
        this.smtpPort = MailConstant.SMTP_PORT_MAP.get(company);
    }


    public MailAddress getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(MailAddress mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }
}
