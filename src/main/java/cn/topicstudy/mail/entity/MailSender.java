package cn.topicstudy.mail.entity;

/**
 * 发件人
 */
public class MailSender {
    // 发件人邮箱地址
    private MailAddress mailAddress;

    // 发件人邮箱授权码
    private String pwd;

    // 发件人邮箱的SMTP服务器地址，例如：smtp.sohu.com
    private String smtp;

    // 发件人邮箱的SMTP服务器端口，默认25
    private Integer smtpPort = 25;

    public MailSender(MailAddress mailAddress, String pwd, String smtp, Integer smtpPort) {
        this.mailAddress = mailAddress;
        this.pwd = pwd;
        this.smtp = smtp;
        this.smtpPort = smtpPort;
    }

    public MailSender(MailAddress mailAddress, String pwd) {
        this.mailAddress = mailAddress;
        this.pwd = pwd;
    }

    public MailSender() {
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

    @Override
    public String toString() {
        return "MailSender{" +
                "mailAddress=" + mailAddress +
                ", pwd='" + pwd + '\'' +
                ", smtp='" + smtp + '\'' +
                ", smtpPort=" + smtpPort +
                '}';
    }
}
