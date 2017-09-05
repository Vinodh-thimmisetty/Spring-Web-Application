package com.vinodh.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.util.custom.annotations.PasswordMatches;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatches, UserRegistrationForm> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		// nothing to do

	}

	@Override
	public boolean isValid(UserRegistrationForm value, ConstraintValidatorContext context) {
		return StringUtils.equals(value.getUserPassword(), value.getUserPasswordConfirm());
	}

}
