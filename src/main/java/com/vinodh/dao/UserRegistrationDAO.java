package com.vinodh.dao;

import java.util.List;

import com.vinodh.entity.ApplicationUser;

public interface UserRegistrationDAO {
	List<String> loadCountryDetails();

	List<String> loadStateDetails(String searchterm);

	void saveUserDetails(ApplicationUser applicationUser); 

	boolean isValidEmail(String userEmail);

	boolean isValidUserName(String userName);

	List<ApplicationUser> loadAllUserDetails();

	int deleteUser(int userId);
}
