package com.wjh.mail.common;

/**
 * 常用的邮件商
 */
public enum CommonMailProviderEnum {
    SOHU("sohu", "搜狐邮箱"),
    QQ("qq", "QQ邮箱"),
    ;

    private String name;
    private String desc;

    CommonMailProviderEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
