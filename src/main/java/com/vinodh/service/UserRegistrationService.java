package com.vinodh.service;

import java.util.List;

import com.vinodh.dto.UserRegistrationForm;

public interface UserRegistrationService extends ApplicationService {
	List<String> loadCountryDetails();

	List<String> loadStateDetails(String searchterm);

	void saveUserDetails(UserRegistrationForm registrationForm);

	boolean isValidEmail(String email);

	boolean isValidUserName(String userName);
}
