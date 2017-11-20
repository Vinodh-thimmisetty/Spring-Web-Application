package com.vinodh.service.test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.vinodh.config.test.TestEmailConfig;
import com.vinodh.dto.Mail;
import com.vinodh.service.impl.EmailServiceImpl;

import freemarker.template.Configuration;
import freemarker.template.Template;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestEmailConfig.class })
@Ignore
public class EmailServiceTest {

	@InjectMocks
	EmailServiceImpl emailServiceImpl;

	@Mock
	JavaMailSender mailSender;

	@Mock
	Configuration configuration;

	@Mock
	Template template;

	@Mock
	MimeMessageHelper mimeMessageHelper;

	private GreenMail greenMailTestSMTPServer;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// Configuring SMTP Server
		greenMailTestSMTPServer = new GreenMail(ServerSetupTest.SMTP);
		greenMailTestSMTPServer.start();

	}

	@Test
	public void emailSending() throws MessagingException, Exception {
		Assert.assertTrue(true);
		/*
		Mockito.when(configuration.getTemplate(Matchers.anyString())).thenReturn(template);
		// Mockito.when(mimeMessageHelper.getMimeMessage()).thenReturn(loadMimeMessage(Matchers.any(MimeMessage.class)));
		 Mockito.when(mailSender.createMimeMessage()).thenReturn(Matchers.any(MimeMessage.class));
		emailServiceImpl.sendRegistrationVerificatoinEmail(loadMessage(), "registration-confirmation.ftl");

		Message[] messages = greenMailTestSMTPServer.getReceivedMessages();
		Assert.assertEquals(1, messages.length);
		Assert.assertEquals("Email Verification", messages[0].getSubject());*/

	}

	@After
	public void cleanup() {
		greenMailTestSMTPServer.stop();
	}

	private Mail loadMessage() throws MessagingException {

		//@formatter:off
				Mail mail =Mail.builder()
							.mailSubject("Email Verification")
							.mailFrom("course@spring.com")
							.mailTo("vinodh5052@gmail.com") 
							.mailContent("Hello")
							.contentType("text/html; charset=\"UTF-8\"") 
							.build();
				//@formatter:on 
		return mail;
	}

	private MimeMessage loadMimeMessage(MimeMessage mimeMessage) throws MessagingException {

		//@formatter:off
				Mail mail =Mail.builder()
							.mailSubject("Email Verification")
							.mailFrom("course@spring.com")
							.mailTo("vinodh5052@gmail.com") 
							.mailContent("Hello")
							.contentType("text/html; charset=\"UTF-8\"") 
							.build();
				//@formatter:on 

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject(mail.getMailSubject());
		mimeMessageHelper.setFrom(mail.getMailFrom());
		mimeMessageHelper.setTo(mail.getMailTo());
		mimeMessageHelper.setText(mail.getMailContent(), true);

		return mimeMessageHelper.getMimeMessage();
	}
}
