package com.vinodh.config.test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class TestEmailConfig {

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.port", "3025");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("vinodh5052@gmail.com", "Hadoop@664246");
			}
		};

		Session session = Session.getInstance(properties, auth);

		return mailSender;
	}

	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();
		fmConfigFactoryBean.setTemplateLoaderPath("classpath:/templates/");
		return fmConfigFactoryBean;
	}
}
