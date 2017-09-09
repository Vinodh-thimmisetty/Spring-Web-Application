package com.vinodh.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import javax.mail.MessagingException;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.dto.Mail;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.entity.ApplicationUser;
import com.vinodh.service.EmailService;
import com.vinodh.service.impl.UserRegistrationServiceImpl;

public class UserRegistrationServiceTest {

	@InjectMocks
	UserRegistrationServiceImpl userRegistrationService;

	@Mock
	EmailService emailService;

	@Mock
	UserRegistrationDAO userRegistrationDAO;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loadCountryDetails_EmptyList() {
		when(userRegistrationDAO.loadCountryDetails()).thenReturn(Collections.emptyList());
		assertTrue(CollectionUtils.isEmpty(userRegistrationService.loadCountryDetails()));
	}

	@Test
	public void loadCountryDetails_NonEmptyList() {
		when(userRegistrationDAO.loadCountryDetails())
				.thenReturn(Arrays.asList("India", "Australia", "South Africa", "England", "USA"));
		assertTrue(CollectionUtils.isNotEmpty(userRegistrationService.loadCountryDetails()));
		assertEquals(5, userRegistrationService.loadCountryDetails().size());
	}

	@Test
	public void loadStateDetails_EmptyList() {
		when(userRegistrationDAO.loadStateDetails(anyString())).thenReturn(Collections.emptyList());
		assertTrue(CollectionUtils.isEmpty(userRegistrationService.loadStateDetails(anyString())));
	}

	@Test
	public void loadStateDetails_NonEmptyList() {
		when(userRegistrationDAO.loadStateDetails("India"))
				.thenReturn(Arrays.asList("Andhra Pradesh", "Tamilnadu", "Karnataka", "Mumbai", "West Bengal"));
		assertTrue(CollectionUtils.isNotEmpty(userRegistrationService.loadStateDetails("India")));
		assertEquals(5, userRegistrationService.loadStateDetails("India").size());
		assertTrue(CollectionUtils.isEmpty(userRegistrationService.loadStateDetails("Yoo")));
	}

	@Test
	public void validEmail() {
		when(userRegistrationDAO.isValidEmail(anyString())).thenReturn(true);
		assertTrue(userRegistrationService.isValidEmail(anyString()));
	}

	@Test
	public void inValidEmail() {
		when(userRegistrationDAO.isValidEmail(anyString())).thenReturn(false);
		assertFalse(userRegistrationService.isValidEmail(anyString()));
	}

	@Test
	public void validUsername() {
		when(userRegistrationDAO.isValidUserName(anyString())).thenReturn(true);
		assertTrue(userRegistrationService.isValidUserName(anyString()));
	}

	@Test
	public void inValidUsername() {
		when(userRegistrationDAO.isValidUserName(anyString())).thenReturn(false);
		assertFalse(userRegistrationService.isValidUserName(anyString()));
	}

	@Test
	public void saveUserDetails() throws MessagingException {
		doNothing().when(userRegistrationDAO).saveUserDetails(sampleApplicationUser(sampleDTO()));
		doNothing().when(emailService).sendRegistrationVerificatoinEmail(any(Mail.class), anyString());
		userRegistrationService.saveUserDetails(sampleDTO());
	}

	/**
	 * Sample DTO with No Violations
	 * 
	 * @return
	 */
	private UserRegistrationForm sampleDTO() {
		//@formatter:off
				return UserRegistrationForm.builder()
						.firstName("Vinodh")
						.lastName("Thimmisetty")
						.userName("vinodh5052")
						.userPassword("vinodh1234")
						.userPasswordConfirm("vinodh1234")
						.userEmail("vinodh5052@gmail.com")
						.country("India")
						.state("Andhra Pradesh")
						.gender("Male")
						.phone("1234567890")
						.build();
	   //@formatter:off
	}
	
	/**
	 * Sample DTO with No Violations
	 * 
	 * @return
	 */
	private ApplicationUser sampleApplicationUser(UserRegistrationForm registrationForm) {
		return new DozerBeanMapper().map(registrationForm, ApplicationUser.class);
 	}
}
