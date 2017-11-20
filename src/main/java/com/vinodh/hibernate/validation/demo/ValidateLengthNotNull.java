package com.vinodh.hibernate.validation.demo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class ValidateLengthNotNull implements ConstraintValidator<IgnoreBlank, String> {

	@Override
	public void initialize(IgnoreBlank constraintAnnotation) {
		constraintAnnotation.length();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isNotBlank(value)) {
			
		}
		return false;
	}

}
