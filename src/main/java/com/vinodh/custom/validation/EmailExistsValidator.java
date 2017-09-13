package com.vinodh.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vinodh.service.UserRegistrationService;
import com.vinodh.util.custom.annotations.IsEmailExists;

@Component
public class EmailExistsValidator implements ConstraintValidator<IsEmailExists, String> {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@Override
	public void initialize(IsEmailExists constraintAnnotation) {
		// nothing to do
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// Check if Email already exits in Database
		return userRegistrationService.isValidEmail(email);

	}

	// Used when validation gets instantiated manually during testing.
	protected void setUserRegistrationService(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}
}
