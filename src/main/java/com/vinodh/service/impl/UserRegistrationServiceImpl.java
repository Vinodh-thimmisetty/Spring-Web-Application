package com.vinodh.service.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.dto.Mail;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.entity.ApplicationUser;
import com.vinodh.service.EmailService;
import com.vinodh.service.UserRegistrationService;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRegistrationDAO userRegistrationDAO;

	@Autowired
	private EmailService emailService;

	@Override
	public List<String> loadCountryDetails() {
		return userRegistrationDAO.loadCountryDetails();
	}

	@Override
	public List<String> loadStateDetails(String searchterm) {
		return userRegistrationDAO.loadStateDetails(searchterm);
	}

	@Override
	public void saveUserDetails(UserRegistrationForm registrationForm) {
		// Convert the form Object(DTO) to Entity
		Mapper mapper = new DozerBeanMapper();
		ApplicationUser applicationUser = mapper.map(registrationForm, ApplicationUser.class);
		// Save the Entity
		userRegistrationDAO.saveUserDetails(applicationUser);
		// Send Registration Confirmation Link to User
		try {
			//@formatter:off
			Mail mail =Mail.builder()
						.mailSubject("Email Verification")
						.mailFrom("course@spring.com")
						.mailTo("vinodh5052@gmail.com") 
						.contentType("text/html; charset=\"UTF-8\"") 
						.build();
			//@formatter:on

			emailService.sendRegistrationVerificatoinEmail(mail, "registration-confirmation.ftl");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
