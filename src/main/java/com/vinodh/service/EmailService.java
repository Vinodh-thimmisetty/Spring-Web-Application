package com.vinodh.service;

import javax.mail.MessagingException;

import com.vinodh.dto.Mail;

public interface EmailService {

	void sendRegistrationVerificatoinEmail(Mail mail, final String templateName) throws MessagingException;
}
