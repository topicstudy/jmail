介绍

jmail用于发邮件

# 依赖
javax.mail >=1.6.2


# 如何使用

* pom.xml中添加依赖
```xml
<dependency>
    <groupId>io.github.topicstudy</groupId>
    <artifactId>jmail</artifactId>
    <version>RELEASE</version>
</dependency>
```


* 示例-发简单邮件

  简单邮件：邮件内容只有文本，80%的发邮件需求都是发这种邮件

```java
// 发件人
MailSender sender = new MailSender();
sender.setMailAddress(new MailAddress("wjh@sohu.com", "搜狐测试发送者"));//TODO
sender.setPwd("ABCDQAZRTY");//TODO
sender.setSmtp("smtp.sohu.com");
sender.setSmtpPort(25);

// 收件人
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

* 示例-发完整邮件

  完整邮件：邮件内容有文本、图片和附件，即邮件内容结构是完整的

  ```java
  // 发送
  MailUtil.send(createCompleteMail());
  
  
  /**
   * 创建完整邮件
   */
  private Mail createCompleteMail() {
      String pathPrefix = "src/test/resources/";
      
      // 发件人
      MailSender sender = new MailSender();
      sender.setMailAddress(new MailAddress("wjh@sohu.com", "搜狐测试发送者"));//TODO
      sender.setPwd("ABCDQAZRTY");//TODO
      sender.setSmtp("smtp.sohu.com");
      sender.setSmtpPort(25);
  
      // 收件人
      List<MailAddress> recipientList = new ArrayList();
      recipientList.add(new MailAddress("song@qq.com", "接收者3493"));//TODO
      recipientList.add(new MailAddress("kfg@163.com", "接收者163"));//TODO
  
      // 内嵌图片
      List<File> pics = new ArrayList();
      pics.add(new File(pathPrefix + "p1.png"));
      pics.add(new File(pathPrefix + "p2.png")); 
  
      // 附件
      List<File> attachments = new ArrayList();
      attachments.add(new File(pathPrefix + "a1.txt"));
      attachments.add(new File(pathPrefix + "a2.txt"));
      
      Mail mail = new Mail();
      mail.setSender(sender);
      mail.setRecipientList(recipientList);
      mail.setSubject("搜狐测试主题-完整邮件");
      mail.setHtml("<h1>1a汉字</h1><img src='p1.png'><img src='p2.png'>");
      mail.setPicList(pics);
      mail.setAttachmentList(attachments);
  
      return mail;
  }
  
  ```


# 常用邮件商

| 邮件商   | SMTP服务器    | SMTP端口 |
| -------- | ------------- | -------- |
| 搜狐邮箱 | smtp.sohu.com | 25       |
| QQ邮箱   | smtp.qq.com   | 25       |

  

