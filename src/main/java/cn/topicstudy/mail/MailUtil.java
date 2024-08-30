package cn.topicstudy.mail;

import cn.topicstudy.jutil.basic.text.StringUtil;
import cn.topicstudy.mail.common.Constant;
import cn.topicstudy.mail.entity.Mail;
import cn.topicstudy.mail.entity.MailSender;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 使用入口
 */
public class MailUtil {
    /**
     * 判断字符串是否是邮箱地址
     */
    public static boolean isMail(String s) {
        if (StringUtil.isBlank(s)) return false;
        String reg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return s.matches(reg);
    }

    /**
     * 将邮件保存到本地
     *
     * @param absolutePath 绝对路径
     */
    public static void saveEmail(Message mail, String absolutePath) {
        try {
            FileOutputStream fos = new FileOutputStream(absolutePath);
            mail.writeTo(fos);
            fos.close();
            if (fos != null) fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发邮件
     */
    public static void send(Mail mail) {
        try {
            // 创建邮件
            MailFactory mailFactory = new MailFactory();
            Session session = Session.getInstance(new Properties());
            session.setDebug(Constant.IS_DEV);
            Message message = mailFactory.createEmail(mail, session);
            if (Constant.NEED_BACKUP && StringUtil.isNotBlank(Constant.BACKUP_MAIL_PATH)) {
                MailUtil.saveEmail(message, Constant.BACKUP_MAIL_PATH);
            }

            // 发邮件
            Transport transport = session.getTransport();
            MailSender sender = mail.getSender();
            // 邮件客户端连smtp服务器
            transport.connect(
                    sender.getSmtp(),
                    sender.getSmtpPort(),
                    sender.getMailAddress().getName(),// 例如：zxj@qq.com中的zxj
                    sender.getPwd()
            );
            transport.sendMessage(message, mail.getRecipientAddresses());
            transport.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
