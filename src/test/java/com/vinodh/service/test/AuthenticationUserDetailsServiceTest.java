package com.vinodh.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vinodh.domain.AuthenticationUser;
import com.vinodh.domain.AuthenticationUser.Role;
import com.vinodh.repository.AuthenticationUserDetailsDAO;
import com.vinodh.service.impl.AuthenticationUserDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationUserDetailsServiceTest {

	public static final String AUTH_USER = "VINODH";
	public static final String CURRENT_USER = "Yoyo";
	public static final AuthenticationUser AUTHENITCATION_USER = AuthenticationUser.builder().userId("101")
			.userFirstName("Vinodh Kumar").userLastName("Thimmisetty").applicationName("spring-security")
			.role(Role.APPLICATION_ADMIN).userEmail("vinu@test.com").userPhone("7412563987").build();

	@InjectMocks
	AuthenticationUserDetailsServiceImpl authenticationUserDetailsServiceImpl;

	@Mock
	AuthenticationUserDetailsDAO authenticationUserDetailsDAO;

	@Mock
	Validator mockValidator;

	@Mock
	ConstraintViolation<AuthenticationUser> mockViolation;

	@Before
	public void setup() {

	}

	@Test
	public void listAllAuthUsers() {
		when(authenticationUserDetailsDAO.listAllAuthUsers()).thenReturn(Collections.emptyList());
		assertTrue(authenticationUserDetailsServiceImpl.listAllAuthUsers().isEmpty());
	}

	@Test
	public void getAuthUser() {
		when(authenticationUserDetailsDAO.getAuthUser(AUTH_USER)).thenReturn(AUTHENITCATION_USER);
		assertEquals(authenticationUserDetailsServiceImpl.getAuthUser(AUTH_USER), AUTHENITCATION_USER);
	}

	@Test
	public void insertAuthUser() {
		when(authenticationUserDetailsDAO.insertAuthUser(AUTHENITCATION_USER, CURRENT_USER)).thenReturn(1);
		assertEquals(authenticationUserDetailsServiceImpl.insertAuthUser(AUTHENITCATION_USER, CURRENT_USER), 1);
	}

	@Test
	public void updateAuthUser() {
		when(authenticationUserDetailsDAO.updateAuthUser(AUTHENITCATION_USER, CURRENT_USER)).thenReturn(1);
		assertEquals(authenticationUserDetailsServiceImpl.updateAuthUser(AUTHENITCATION_USER, CURRENT_USER), 1);
	}

	@Test
	public void mergeAuthUser() {
		when(authenticationUserDetailsDAO.mergeAuthUser(AUTHENITCATION_USER, CURRENT_USER)).thenReturn(1);
		assertEquals(authenticationUserDetailsServiceImpl.mergeAuthUser(AUTHENITCATION_USER, CURRENT_USER), 1);
	}

	@Test
	public void deleteAuthUser() {
		when(authenticationUserDetailsDAO.deleteAuthUser(AUTH_USER, CURRENT_USER)).thenReturn(1);
		assertEquals(authenticationUserDetailsServiceImpl.deleteAuthUser(AUTH_USER, CURRENT_USER), 1);
	}

	@Test
	public void addAuthUser() {
		when(authenticationUserDetailsDAO.insertAuthUser(AUTHENITCATION_USER, CURRENT_USER)).thenReturn(1);
		assertEquals(authenticationUserDetailsServiceImpl.addAuthUser(AUTHENITCATION_USER, CURRENT_USER), 1);
	}

}
