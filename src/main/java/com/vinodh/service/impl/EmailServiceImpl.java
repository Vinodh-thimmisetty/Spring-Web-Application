package com.vinodh.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.vinodh.dto.Mail;
import com.vinodh.service.EmailService;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 
 * This is utility Service used to send emails using Gmail SMTP server.
 * 
 * Freemarker Templates
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	@Override
	public void sendRegistrationVerificatoinEmail(Mail mail, final String templateName) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// load the Dynamic Values into Map
		Map<String, Object> messageBody = new HashMap<>();
		messageBody.put("greeting", "Hello");
		messageBody.put("verifyLink", "verify");
		// Free marker Template Configurations
		String emailContent = loadEmailContentToFreemarkerTemplate(messageBody, templateName);
		mail.setMailContent(emailContent);

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject(mail.getMailSubject());
		mimeMessageHelper.setFrom(mail.getMailFrom());
		mimeMessageHelper.setTo(mail.getMailTo());
		mimeMessageHelper.setText(mail.getMailContent(), true);

		// Send the Email
		mailSender.send(mimeMessageHelper.getMimeMessage());
	}

	private String loadEmailContentToFreemarkerTemplate(Map<String, Object> model, String templateName) {
		StringBuilder content = new StringBuilder();
		try {
			content.append(
					FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateName), model));
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}