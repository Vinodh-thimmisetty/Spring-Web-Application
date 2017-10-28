package com.vinodh.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.entity.ApplicationUser;
import com.vinodh.util.custom.annotations.IsValidUser;

@Component
public class UserIDValidator implements ConstraintValidator<IsValidUser, Integer> {

	@Autowired
	UserRegistrationDAO userRegistrationDAO;

	@Override
	public boolean isValid(Integer userId, ConstraintValidatorContext context) {
		ApplicationUser applicationUser = userRegistrationDAO.loadUserDetail(userId);
		return (applicationUser == null);
	}

}
