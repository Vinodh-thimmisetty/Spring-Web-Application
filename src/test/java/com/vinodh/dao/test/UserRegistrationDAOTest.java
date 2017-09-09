package com.vinodh.dao.test;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vinodh.config.test.TestSpringApplicationContext;
import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.entity.ApplicationUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestSpringApplicationContext.class })
public class UserRegistrationDAOTest {

	@Autowired
	UserRegistrationDAO userRegistrationDAO;

	@Before
	public void setup() {
		// Add any configurations if required
	}

	@Test
	@Rollback(true)
	public void loadCountries() {
		Assert.assertTrue(CollectionUtils.isNotEmpty(userRegistrationDAO.loadCountryDetails()));
		Assert.assertEquals(2, userRegistrationDAO.loadCountryDetails().size());
	}

	@Test
	public void loadStates() {
		Assert.assertEquals(2, userRegistrationDAO.loadStateDetails("ANDHRA").size());
	}

	@Test
	public void userNameValidation() {
		Assert.assertFalse(userRegistrationDAO.isValidUserName("Vinodh5052"));
		Assert.assertTrue(userRegistrationDAO.isValidUserName("Vinodh5052-success"));
	}

	@Test
	public void userEmailValidation() {
		Assert.assertFalse(userRegistrationDAO.isValidEmail("Vinodh5052@gmail.com"));
		Assert.assertTrue(userRegistrationDAO.isValidUserName("test-Vinodh5052@gmail.com"));
	}

	@Test
	public void saveUser() {
		userRegistrationDAO.saveUserDetails(sampleApplicationUser(sampleDTO()));
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
