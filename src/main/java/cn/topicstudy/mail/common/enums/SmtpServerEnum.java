package cn.topicstudy.mail.common.enums;

public enum SmtpServerEnum {
    SOHU("smtp.sohu.com", 25),
    QQ("smtp.qq.com", 25),
    NETEASE("smtp.163.com", 25),
    ;

    private String serverAddress;
    private Integer serverPort;


    SmtpServerEnum(String serverAddress, Integer serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public Integer getServerPort() {
        return serverPort;
    }
}
