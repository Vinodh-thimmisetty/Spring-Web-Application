package com.vinodh.dao;

import java.util.List;

public interface UserRegistrationDAO {
	List<String> loadCountryDetails();

	List<String> loadStateDetails(String searchterm);
}
