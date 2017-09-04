package com.vinodh.service;

import java.util.List;

public interface UserRegistrationService {
	List<String> loadCountryDetails();

	List<String> loadStateDetails(String searchterm);
}
