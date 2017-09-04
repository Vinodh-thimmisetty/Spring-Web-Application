package com.vinodh.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.entity.Country;
import com.vinodh.service.UserRegistrationService;

@Service
@Transactional
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRegistrationDAO userRegistrationDAO;

	@Override
	public List<Country> loadCountryDetails() {
		return userRegistrationDAO.loadCountryDetails();
	}

}
