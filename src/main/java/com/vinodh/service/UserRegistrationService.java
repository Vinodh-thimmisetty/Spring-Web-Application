package com.vinodh.service;

import java.util.List;

import com.vinodh.dto.UserRegistrationForm;

public interface UserRegistrationService {
	List<String> loadCountryDetails();

	List<String> loadStateDetails(String searchterm);

	void saveUserDetails(UserRegistrationForm registrationForm);
}
