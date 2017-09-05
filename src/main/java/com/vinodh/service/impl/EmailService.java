package com.vinodh.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * This is utility Service used to send emails
 * 
 * Freemarker Templates
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	//@Autowired
	private Configuration configuration;

	public void sendRegistrationVerificatoinEmail() {

		// Prepare the Email Content
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
				messageHelper.setSubject("Email Verification");
				messageHelper.setFrom(new InternetAddress("course@spring.com"));
				messageHelper.setTo(new InternetAddress("tvinodhkumar123@gmail.com"));
				messageHelper.setText("Hello Vinodh");

				// load the Dynamic Values into Map
				Map<String, Object> model = new HashMap<>();
				model.put("greeting", "Hello");
				model.put("verifyLink", "verify");

				Template template = configuration.getTemplate("/templates/registration-confirmation.ftl");

				messageHelper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(template, model), true);

			}
		};

		mailSender.send(messagePreparator);
	}
}
