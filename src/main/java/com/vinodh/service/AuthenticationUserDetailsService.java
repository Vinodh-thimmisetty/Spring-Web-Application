package com.vinodh.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vinodh.domain.AuthenticationUser;

/**
 * 
 * Application Authorization CRUD operations related service
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
public interface AuthenticationUserDetailsService extends UserDetailsService {

	List<AuthenticationUser> listAllAuthUsers();

	AuthenticationUser getAuthUser(String userId);

	int insertAuthUser(AuthenticationUser authenticationUser, String loggedInUser);

	int updateAuthUser(AuthenticationUser authenticationUser, String loggedInUser);

	/**
	 * 
	 * Useful when going with MyBatis where SQLs will be manually written.
	 * 
	 * If going with Hibernate, above mentioned individual INSERT/UPDATE methods can
	 * be used
	 * 
	 * @param authenticationUser
	 * @param loggedInUser
	 * @return
	 */
	int mergeAuthUser(AuthenticationUser authenticationUser, String loggedInUser);

	int deleteAuthUser(String userId, String loggedInUser);

	int addAuthUser(AuthenticationUser authenticationUser, String loggedInUserNameFromSpringSecurityContext);

}
