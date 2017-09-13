package com.vinodh.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vinodh.service.UserRegistrationService;
import com.vinodh.util.custom.annotations.IsUserNameExists;

@Component
public class UserNameExistValidator implements ConstraintValidator<IsUserNameExists, String> {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@Override
	public void initialize(IsUserNameExists constraintAnnotation) {
		// nothing to do
	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		return userRegistrationService.isValidUserName(userName);
	}

	// Used when validation gets instantiated manually during testing.
	protected void setUserRegistrationService(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}
}
