package cn.topicstudy.mail.common;


import cn.topicstudy.jutil.basic.text.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MailConstant {
    /*
     * 开发模式
     *  开发模式下会备份所发送的邮件
     */
    public static Boolean DEBUG = false;
    public static String SAVE_MAIL_ABSOLUTE_PATH;// 保存邮件到服务器上的路径

    static {
        try {
            if (DEBUG) {
                if (StringUtil.isBlank(SAVE_MAIL_ABSOLUTE_PATH)
                        || !new File(SAVE_MAIL_ABSOLUTE_PATH).exists()) {
                    throw new Exception("debug模式下【SAVE_MAIL_ABSOLUTE_PATH】不可为空");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * smtp服务器
     */
    public static Map<String, String> SMTP_MAP = new HashMap();

    static {
        SMTP_MAP.put("sohu", "smtp.sohu.com");
        SMTP_MAP.put("qq", "smtp.qq.com");
    }

    /*
     * smtp服务器端口
     */
    public static Map<String, Integer> SMTP_PORT_MAP = new HashMap();

    static {
        SMTP_PORT_MAP.put("sohu", 25);
        SMTP_PORT_MAP.put("qq", 25);
    }
}
