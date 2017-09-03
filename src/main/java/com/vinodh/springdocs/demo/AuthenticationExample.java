package com.vinodh.springdocs.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * 1. UserName & Password will be passed to UsernamePasswordAuthenticationToken
 * 2. AuthenticationManager will accept & validate the Credentials for
 * authenticated request and returns new Authentication instance with Roles etc.
 * 3. Spring Security Context is established with SecurityContextHolder holding
 * USER(Principle), AUTHORITIES etc.
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Slf4j
public class AuthenticationExample {

	private static SampleAuthenticationManager authenticationManager = new SampleAuthenticationManager();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		log.info("Enter User Name::");
		String userName = scanner.nextLine();

		log.info("Enter Password::");
		String userPwd = scanner.nextLine();

		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(userName, userPwd);
			Authentication response = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(response);

			log.info("Spring Security Context::{}", SecurityContextHolder.getContext().getAuthentication());

		} catch (Exception e) {
			log.error("Failed to Authenticate ::{}", e);
		} finally {
			scanner.close();
		}

	}

	static class SampleAuthenticationManager implements AuthenticationManager {

		static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
		static {
			AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		/**
		 * Valid Only IF user Name is same as Password
		 * 
		 */
		@Override
		public Authentication authenticate(Authentication authentication) {
			if (authentication.getName().equals(authentication.getCredentials())) {
				return new UsernamePasswordAuthenticationToken(authentication.getName(),
						authentication.getCredentials(), AUTHORITIES);
			}
			return null;
		}

	}
 

}
