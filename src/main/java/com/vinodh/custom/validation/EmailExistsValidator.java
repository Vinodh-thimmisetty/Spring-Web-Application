package com.vinodh.custom.validation;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.util.custom.annotations.IsEmailExists;
@Transactional
public class EmailExistsValidator implements ConstraintValidator<IsEmailExists, String> {

	@Autowired
	private UserRegistrationDAO registrationDAO;

	@Override
	public void initialize(IsEmailExists constraintAnnotation) {
		// nothing to do
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// Check if Email already exits in Database
		return registrationDAO.isValidEmail(email);

	}

}
