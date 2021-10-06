# 介绍

该应用用于发邮件（以后会增加收邮件）

# 使用

依赖

```xml

```

例子：

```java
MailSender sender = new MailSender();
sender.setMailAddress(new MailAddress("wjh@sohu.com", "搜狐测试发送者"));//TODO
sender.setPwd("ABCDQAZRTY");//TODO
sender.setSmtp("smtp.sohu.com");
sender.setSmtpPort(25);

List<MailAddress> recipientList = new ArrayList();
recipientList.add(new MailAddress("song@qq.com", "接收者3493"));//TODO
recipientList.add(new MailAddress("kfg@163.com", "接收者163"));//TODO

Mail mail = new Mail();
mail.setSender(sender);
mail.setRecipientList(recipientList);
mail.setSubject("搜狐测试主题-简单邮件");
mail.setHtml("【新浪】验证码是1234，不可告诉任何人！");

 MailUtil.send(mail);
```



# 常用邮件商

| 邮件商   | SMTP服务器    | SMTP端口 |
| -------- | ------------- | -------- |
| 搜狐邮箱 | smtp.sohu.com | 25       |
| QQ邮箱   | smtp.qq.com   | 25       |
|          |               |          |

