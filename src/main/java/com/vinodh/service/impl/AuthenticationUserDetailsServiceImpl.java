package com.vinodh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vinodh.domain.AuthenticationUser;
import com.vinodh.repository.AuthenticationUserDetailsDAO;
import com.vinodh.service.AuthenticationUserDetailsService;

@Service
public class AuthenticationUserDetailsServiceImpl implements AuthenticationUserDetailsService {

	@Autowired
	AuthenticationUserDetailsDAO authenticationUserDetailsDAO;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return null;
	}

	@Override
	public List<AuthenticationUser> listAllAuthUsers() {
		return authenticationUserDetailsDAO.listAllAuthUsers();
	}

	@Override
	public AuthenticationUser getAuthUser(String userId) {
		return authenticationUserDetailsDAO.getAuthUser(userId);
	}

	@Override
	public int insertAuthUser(AuthenticationUser authenticationUser, String loggedInUser) {
		return authenticationUserDetailsDAO.insertAuthUser(authenticationUser, loggedInUser);
	}

	@Override
	public int updateAuthUser(AuthenticationUser authenticationUser, String loggedInUser) {
		return authenticationUserDetailsDAO.updateAuthUser(authenticationUser, loggedInUser);
	}

	@Override
	public int mergeAuthUser(AuthenticationUser authenticationUser, String loggedInUser) {
		return authenticationUserDetailsDAO.mergeAuthUser(authenticationUser, loggedInUser);
	}

	@Override
	public int deleteAuthUser(String userId, String loggedInUser) {
		return authenticationUserDetailsDAO.deleteAuthUser(userId, loggedInUser);
	}

	@Override
	public int addAuthUser(AuthenticationUser authenticationUser, String loggedInUser) { 
		return authenticationUserDetailsDAO.insertAuthUser(authenticationUser, loggedInUser);
	}

}
