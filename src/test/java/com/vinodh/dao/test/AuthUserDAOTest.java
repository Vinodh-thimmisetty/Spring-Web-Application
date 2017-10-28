package com.vinodh.dao.test;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vinodh.config.test.TestSpringApplicationContext;
import com.vinodh.domain.AuthenticationUser;
import com.vinodh.domain.AuthenticationUser.Role;
import com.vinodh.repository.AuthenticationUserDetailsDAO;

/**
 * 
 * 
 * 
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestSpringApplicationContext.class })
public class AuthUserDAOTest {

	private static final String LOGGEDIN_USER = "Vinodh";

	@Autowired(required = true)
	private AuthenticationUserDetailsDAO authenticationUserDetailsDAO;

	private AuthenticationUser authenticationUser;

	@Before
	public void setup() {
		authenticationUser = AuthenticationUser.builder().userId("500").userFirstName("Yo Yo").userLastName("Brother")
				.userEmail("yo@test.com").userPhone("7894561230").applicationName("spring-security")
				.role(Role.SUPER_ADMIN).build();
	}

	@Test
	public void listOfUsers() {
		List<AuthenticationUser> authenticationUsers = authenticationUserDetailsDAO.listAllAuthUsers();
		Assert.assertTrue(!authenticationUsers.isEmpty());
		Assert.assertEquals(4, authenticationUsers.size());
	}

	@Test
	public void getUser() {
		AuthenticationUser authenticationUser = authenticationUserDetailsDAO.getAuthUser("100");
		Assert.assertNotNull(authenticationUser);
		Assert.assertThat("Auth User exists", authenticationUser.getUserFirstName(),
				Matchers.is(Matchers.equalTo("Vinodh Kumar")));

		authenticationUser = authenticationUserDetailsDAO.getAuthUser("500");
		Assert.assertNull(authenticationUser);
	}

	@Test
	public void insertUser() {
		int insertedRecords = authenticationUserDetailsDAO.insertAuthUser(authenticationUser, LOGGEDIN_USER);
		Assert.assertThat("User with id" + authenticationUser.getUserId() + " is inserted", insertedRecords,
				Matchers.is(1));

	}

	@Test
	public void updateUser() {
		int updatedRecords = authenticationUserDetailsDAO.updateAuthUser(authenticationUser, LOGGEDIN_USER);
		Assert.assertThat("User with id" + authenticationUser.getUserId() + " doesnt exists", updatedRecords,
				Matchers.is(0));

		authenticationUser.setUserId("100");
		updatedRecords = authenticationUserDetailsDAO.updateAuthUser(authenticationUser, LOGGEDIN_USER);
		Assert.assertThat("User with id" + authenticationUser.getUserId() + "exists and updated", updatedRecords,
				Matchers.is(1));

	}

	@Test
	public void deleteUser() {
		int deletedRecords = authenticationUserDetailsDAO.deleteAuthUser("999", LOGGEDIN_USER);
		Assert.assertThat("User with id" + authenticationUser.getUserId() + " doesnt exists", deletedRecords,
				Matchers.is(0));

		authenticationUser.setUserId("100");
		deletedRecords = authenticationUserDetailsDAO.updateAuthUser(authenticationUser, LOGGEDIN_USER);
		Assert.assertThat("User with id " + authenticationUser.getUserId() + "exists and Deleted", deletedRecords,
				Matchers.is(1));
	}

	@Test
	public void mergeUser() {
		int rowsEffected = authenticationUserDetailsDAO.mergeAuthUser(authenticationUser, LOGGEDIN_USER);
		Assert.assertThat("User with id" + authenticationUser.getUserId() + " doesnt exists, so Inserting...",
				rowsEffected, Matchers.is(1));
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void mergeUser_Exception() {
		// Foreign Key Integrity Violation
		authenticationUser.setUserId("999");
		authenticationUserDetailsDAO.mergeAuthUser(authenticationUser, LOGGEDIN_USER);

	}

}
