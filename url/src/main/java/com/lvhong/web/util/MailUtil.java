//package com.lvhong.web.util;
//
//import java.util.Date;
//import java.util.Properties;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class MailUtil {
//	/**
//	 * 发送邮件工具
//	 * @param sendEmailAccount 发送人账号
//	 * @param sendEmailPassword 发送人smtp密码
//	 * @param emailSmtpHost 发送人smtp主机
//	 * @param receiveMailAccount 接受人账号
//	 * @param sendMailNickName 发送人昵称
//	 * @param receiveMailNickName 接受人昵称
//	 * @param theme 邮件主题
//	 * @param content 邮件正文
//	 * @throws Exception
//	 * @author lvhong
//	 * @since jdk1.8
//	 */
//	public static void sendMail(String sendEmailAccount, String sendEmailPassword, String emailSmtpHost,
//			String receiveMailAccount, String sendMailNickName, String receiveMailNickName, String theme,
//			String content) throws Exception {
//		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
//		Properties props = new Properties(); // 参数配置
//		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
//		props.setProperty("mail.smtp.host", emailSmtpHost); // 发件人的邮箱的 SMTP 服务器地址
//		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
//		final String smtpPort = "465";
//		props.setProperty("mail.smtp.port", smtpPort);
//		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.setProperty("mail.smtp.socketFactory.fallback", "false");
//		props.setProperty("mail.smtp.socketFactory.port", smtpPort);
//
//		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
//		Session session = Session.getDefaultInstance(props);
//		session.setDebug(true);
//
//		// 3. 创建一封邮件
//
//		MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveMailAccount, sendMailNickName,
//				receiveMailNickName, theme, content);
//
//		// 4. 根据 Session 获取邮件传输对象
//		Transport transport = session.getTransport();
//
//		transport.connect(sendEmailAccount, sendEmailPassword);
//
//		// 密送人
//		transport.sendMessage(message, message.getAllRecipients());
//
//		// 7. 关闭连接
//		transport.close();
//	}
//
//	/**
//	 * 邮件正文编写
//	 * @author lvhong
//	 * @param session 邮件会话
//	 * @param sendMail 发送人账号
//	 * @param receiveMail 接受人账号
//	 * @param sendMailNickName 发件人昵称
//	 * @param receiveMailNickName 收件人昵称
//	 * @param theme 主题
//	 * @param content 正文
//	 * @return MimeMessage 信息响应
//	 * @throws Exception
//	 * @since jdk1.8
//	 */
//	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,
//			String sendMailNickName, String receiveMailNickName, String theme, String content) throws Exception {
//		// 1. 创建一封邮件
//		MimeMessage message = new MimeMessage(session);
//
//		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
//		message.setFrom(new InternetAddress(sendMail, sendMailNickName, "UTF-8"));
//
//		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
//		message.setRecipient(MimeMessage.RecipientType.TO,
//				new InternetAddress(receiveMail, receiveMailNickName, "UTF-8"));
//
//		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
//		message.setSubject(theme, "UTF-8");
//
//		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
//		message.setContent(content, "text/html;charset=UTF-8");
//
//		// 6. 设置发件时间
//		message.setSentDate(new Date());
//
//		// 7. 保存设置
//		message.saveChanges();
//
//		return message;
//	}
//}
