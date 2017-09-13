package com.vinodh.custom.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.vinodh.util.custom.annotations.ValidEmail;

@Component
public class CustomEmailValidator implements ConstraintValidator<ValidEmail, String> {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		// nothing to do
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// Java Regexp
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email); 
		return matcher.matches();
	}

}
