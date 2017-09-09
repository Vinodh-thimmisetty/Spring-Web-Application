package com.vinodh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinodh.repository.UserRepository;
import com.vinodh.service.CustomUserDetailService;

@Service
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return null;
	}

}
