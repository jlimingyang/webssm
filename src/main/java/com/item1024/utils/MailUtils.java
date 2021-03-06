package com.item1024.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @author xiongyu
 * @version Create at ：2017/4/18 23:51
 */

public class MailUtils {

    private static String host = "smtp.163.com"; // smtp服务器
    private static String from = "15892898653@163.com"; // 发件人地址
    private static String to = ""; // 收件人地址
    private static String user = "m15892898653@163.com"; // 用户名
    private static String pwd = "xy99568392xy"; // 密码
    private static String subject = ""; // 邮件标题

//    public void setAddress( String to, String subject) {
//
//        this.to = to;
//        this.subject = subject;
//    }


    private MailUtils() {
    }

    public static boolean send(String to, String subject) {
        boolean falg =false;
        Properties props = new Properties();

        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);

        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 加载标题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText("内容");
            contentPart.setContent("html content", "text/html;charset=UTF-8");
            contentPart.setDisposition("1231231");
            multipart.addBodyPart(contentPart);
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, user, pwd);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            falg=true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return falg;
    }

}