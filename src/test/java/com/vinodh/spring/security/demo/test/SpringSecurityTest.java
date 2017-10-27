package com.vinodh.spring.security.demo.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vinodh.spring.security.demo.TestMessageService;
import com.vinodh.util.custom.annotations.WithMockUserInformation;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpringSecurityTest {

	@InjectMocks
	TestMessageService messageService;

	/**
	 * 
	 * org.springframework.security.authentication.UsernamePasswordAuthenticationToken
	 * 
	 */
	@Test
	@WithMockUser(username = "Vinodh", password = "abcd1234", authorities = { "USER", "ADMIN" })
	public void test() {
		Assert.assertNotNull(messageService.getMessage());
		Assert.assertEquals("Vinodh", messageService.getMessage());
	}

	/**
	 * 
	 * org.springframework.security.authentication.AnonymousAuthenticationToken
	 * 
	 */
	@Test
	@WithAnonymousUser
	public void test_anonymous() {
		Assert.assertNotNull(messageService.getMessage());
		Assert.assertEquals("anonymous", messageService.getMessage());
	}

	/*
	 * Custom Authentication Principle
	 * 
	 *
	@Test
	@WithUserDetails
	public void test_userDetails() {
		Assert.assertNotNull(messageService.getMessage());
		Assert.assertEquals("Vinodh", messageService.getMessage());
	}*/
	

	/**
	 * 
	 * WIth Custom Mock Authentication
	 * 
	 */
	@Test
	@WithMockUserInformation
	public void test_custom_user() {
		Assert.assertNotNull(messageService.getMessage());
		Assert.assertEquals("Mocked-Vinodh", messageService.getMessage());
	}
}
