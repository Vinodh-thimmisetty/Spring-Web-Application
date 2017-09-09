package com.vinodh.custom.validation;

import javax.validation.ConstraintValidator;

import org.springframework.web.bind.support.SpringWebConstraintValidatorFactory;
import org.springframework.web.context.WebApplicationContext;

import com.vinodh.service.ApplicationService;
import com.vinodh.service.UserRegistrationService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This Class will be used to Unit test the Spring Application with stand alone
 * mode by Bypassing the flow of original Spring application context.
 * 
 * credits:
 * https://stackoverflow.com/questions/23161752/spring-mockmvc-how-to-mock-custom-validators-running-outside-of-controllers
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Slf4j
public class TestCustomValidationFactory extends SpringWebConstraintValidatorFactory {

	private final WebApplicationContext webApplicationContext;

	private ApplicationService service;

	public TestCustomValidationFactory(WebApplicationContext applicationContext, ApplicationService service) {
		this.webApplicationContext = applicationContext;
		this.service = service;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
		ConstraintValidator<?, ?> constraintValidator = super.getInstance(key);
		if (constraintValidator instanceof EmailExistsValidator) {
			log.info("Validating Custom Email:::");
			EmailExistsValidator emailExistsValidator = (EmailExistsValidator) constraintValidator;
			emailExistsValidator.setUserRegistrationService((UserRegistrationService) service);
			constraintValidator = emailExistsValidator;
		}
		if (constraintValidator instanceof UserNameExistValidator) {
			log.info("Validating Custom User Email:::");
			UserNameExistValidator userNameExistValidator = (UserNameExistValidator) constraintValidator;
			userNameExistValidator.setUserRegistrationService((UserRegistrationService) service);
			constraintValidator = userNameExistValidator;
		}
		return (T) constraintValidator;

	}

	@Override
	protected WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

}
