package com.vinodh.custom.validation;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.util.custom.annotations.IsUserNameExists;
@Transactional
public class UserNameExistValidator implements ConstraintValidator<IsUserNameExists, String> {

	@Autowired
	private UserRegistrationDAO registrationDAO;

	@Override
	public void initialize(IsUserNameExists constraintAnnotation) {
		// nothing to do
	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		return registrationDAO.isValidUserName(userName);
	}

}
