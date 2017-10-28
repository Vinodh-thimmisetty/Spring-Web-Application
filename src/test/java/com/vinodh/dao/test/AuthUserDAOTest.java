package com.vinodh.dao.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vinodh.config.test.TestSpringApplicationContext;
import com.vinodh.domain.AuthenticationUser;
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

	@Autowired(required=true)
	private AuthenticationUserDetailsDAO authenticationUserDetailsDAO;

	@Before
	public void setup() {

	}

	@Test
	public void listOfUsers() {
		List<AuthenticationUser> authenticationUsers = authenticationUserDetailsDAO.listAllAuthUsers();
		Assert.assertTrue(!authenticationUsers.isEmpty());
		Assert.assertEquals(4, authenticationUsers.size());
	}

}
