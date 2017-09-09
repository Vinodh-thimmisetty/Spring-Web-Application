package com.vinodh.springdocs.demo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JavaMail {

	public static void main(String[] args) throws MessagingException {

		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		javax.mail.Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication("##@gmail.com", "###");
			}

		});
		javax.mail.internet.MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("vinodhTest@learningJava.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("vinodh5052@gmail.com"));
		message.setSubject("Hello This is vinodh");
		message.setText("Yo ! Bro , What's up");
		message.setContent("<a href='https://www.google.com' > Jaffa </a>", "text/html");
		javax.mail.Transport.send(message);

		log.info("Message sent successfully");
	}
}
