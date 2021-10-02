package com.wjh.mail.entity;

public class MailAddress {
    // 邮箱地址，例如：zxj@qq.com
    private String address;
    // 邮箱昵称，例如：张小久
    private String nickname;

    // 例如：张小久<zxj@qq.com>
    private String addressWithNickname;
    // 例如：zxj，从address中提取
    private String name;
    // 例如：qq，从address中提取
    private String emailCompany;

    public MailAddress(String address, String nickname) {
        this.address = address;
        this.nickname = nickname;
        this.addressWithNickname = nickname == null ? address : nickname + "<" + address + ">";

        String[] strings = address.split("@");
        String s0 = strings[0];// zxj
        String s1 = strings[1];// qq.com
        this.name = s0;
        this.emailCompany = s1.split("\\.")[0];
    }

    public MailAddress(String address) {
        this(address, null);
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddressWithNickname() {
        return addressWithNickname;
    }

    public void setAddressWithNickname(String addressWithNickname) {
        this.addressWithNickname = addressWithNickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    @Override
    public String toString() {
        return "MailAddress{" +
                "address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                ", addressWithNickname='" + addressWithNickname + '\'' +
                ", name='" + name + '\'' +
                ", emailCompany='" + emailCompany + '\'' +
                '}';
    }
}
