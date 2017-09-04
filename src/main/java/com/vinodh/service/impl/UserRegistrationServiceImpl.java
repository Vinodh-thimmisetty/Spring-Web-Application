package com.vinodh.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.service.UserRegistrationService;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRegistrationDAO userRegistrationDAO;

	@Override
	public List<String> loadCountryDetails() {
		return userRegistrationDAO.loadCountryDetails();
	}

	@Override
	public List<String> loadStateDetails(String searchterm) {
		return userRegistrationDAO.loadStateDetails(searchterm);
	}

}
