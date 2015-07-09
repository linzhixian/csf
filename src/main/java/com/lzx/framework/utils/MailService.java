/** 
 * ©2012 UFun Technology (HK) Limited Corporation. All rights reserved. 
 */
package com.lzx.framework.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	private String host;
	private String from;
	private  List<String> toList;
	
	private String username;
	private String password;
	
	public void sendMail(String subject, String content)
			throws AddressException, MessagingException {

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.host", host);

		// Get session
		props.put("mail.smtp.auth", "true"); // 这样才能通过验证

		MyAuthenticator myauth = new MyAuthenticator(username, password);
		Session session = Session.getDefaultInstance(props, myauth);

		// session.setDebug(true);

		// Define message
		MimeMessage message = new MimeMessage(session);

		// Set the from address
		message.setFrom(new InternetAddress(from));
		for (String to : toList) {
			// Set the to address
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		}

		// Set the subject
		message.setSubject(subject);

		// Set the content
		message.setText(content);

		message.saveChanges();

		Transport.send(message);
	}
	public static void main(String args[]) throws Exception {

		String host = "smtp.ym.163.com"; // 发件人使用发邮件的电子信箱服务器
		String from = "linzhixian@ufun.hk";// 发邮件的出发地（发件人的信箱）
		//String to = "15013827096@139.com"; // 发邮件的目的地（收件人信箱）
		List<String> toList=new ArrayList<String>();
		//toList.add("15013827096@139.com");
		toList.add("linzhixian@ufun.hk");
		//toList.add("15818617022@139.com");
		//toList.add("13802428318@139.com");
		
		MailService ms=new MailService();
		ms.setHost(host);
		ms.setFrom(from);
		ms.setToList(toList);
		ms.setUsername("linzhixian@ufun.hk");
		ms.setPassword("abc967306");
		ms.sendMail("曾国藩的相人三条： 1、功名看器宇 2、事业看精神 3、若要看条理，尽在言语中", "曾国藩的相人三条： 1、功名看器宇 2、事业看精神 3、若要看条理，尽在言语中");

	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getToList() {
		return toList;
	}

	public void setToList(List<String> toList) {
		this.toList = toList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

class MyAuthenticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;

	public MyAuthenticator(String user, String password) {
		this.strUser = user;
		this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}