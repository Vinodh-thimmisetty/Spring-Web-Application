package com.vinodh.web.test;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodh.config.WebSecurityConfig;
import com.vinodh.service.AuthenticationUserDetailsService;
import com.vinodh.service.UserRegistrationService;
import com.vinodh.util.custom.annotations.WithMockUserInformation;
import com.vinodh.web.AuthenticationController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
public class AuthUserControllerTest {

	@InjectMocks
	AuthenticationController authenticationController;

	@Mock
	AuthenticationUserDetailsService authenticationUserDetailsService;
	
	@Mock
	UserRegistrationService userRegistrationService;

	@Autowired
	FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;
	private ObjectMapper jsonMapper;

	@Before
	public void setup() {

		// Enable Mockito
		MockitoAnnotations.initMocks(this);

		// Register the View Resolver if ModelAndView is used instead of String as GET
		// method return type
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);

		// Test the controller as standalone application

		mockMvc = MockMvcBuilders.standaloneSetup(authenticationController)
				.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain)).build();

	}

	@Test
	@WithMockUserInformation
	public void loadUserAuthenticationPage() throws Exception {
		Mockito.when(authenticationUserDetailsService.listAllAuthUsers()).thenReturn(Collections.emptyList());
		Mockito.when(userRegistrationService.loadAllUserDetails()).thenReturn(Collections.emptyList());
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/authHomePage"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("users"))
				.andExpect(MockMvcResultMatchers.model().attribute("users", Matchers.is(Matchers.empty())))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("/admin/authhomepage"));
	}

	@After
	public void cleanup() {

	}
}
