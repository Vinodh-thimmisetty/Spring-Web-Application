package com.vinodh.web.test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodh.config.WebSecurityConfig;
import com.vinodh.domain.AuthenticationUser;
import com.vinodh.domain.AuthenticationUser.Role;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.AuthenticationUserDetailsService;
import com.vinodh.service.UserRegistrationService;
import com.vinodh.util.custom.annotations.WithMockUserInformation;
import com.vinodh.web.AuthenticationController;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebSecurityConfig.class)
@Slf4j
public class AuthUserControllerTest {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
	public static final String SERVER_ERROR = "SERVER_ERROR";
	public static final String STATUS = "STATUS";
	public static final String EFFECTED_ROWS_COUNT = "COUNT";

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
	private String currentUser;

//@formatter:off

	// load initial sample users list. Can be updated in future since marked as SPY
	@Spy
	private List<UserRegistrationForm>	sampleUsersList = Stream.of(UserRegistrationForm.builder()
																							.firstName("Vinodh")
																							.userEmail("vinu@test.com")
																							.phone("1234567890")
																						.build(),
																	UserRegistrationForm.builder()
																							.firstName("Kumar")
																							.userEmail("kumar@test.com")
																							.phone("1234567890")
																						.build())
																.collect(Collectors.toList());
			

	@Spy
	private List<AuthenticationUser> sampleAuthUsersList = Stream.of(AuthenticationUser.builder()
																						.applicationName("spring-security")
																						.role(Role.APPLICATION_USER)
																					.build(),
																	AuthenticationUser.builder()
																						.applicationName("spring-security")
																						.role(Role.APPLICATION_USER)
																					.build())
																.collect(Collectors.toList());
			
//@formatter:on

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

		currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

		jsonMapper = new ObjectMapper();
	}

	@Test
	@WithMockUserInformation
	public void loadUserAuthenticationPage() throws Exception {
		Mockito.when(authenticationUserDetailsService.listAllAuthUsers()).thenReturn(Collections.emptyList());
		Mockito.when(userRegistrationService.loadAllUserDetails()).thenReturn(sampleUsersList);
		mockMvc.perform(MockMvcRequestBuilders.get("/admin/authHomePage"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("users", "registeredUsers", "authorizedAdmin"))
				.andExpect(MockMvcResultMatchers.model().attribute("users", Matchers.is(Matchers.empty())))
				.andExpect(MockMvcResultMatchers.model().attribute("registeredUsers", sampleUsersList))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("/admin/authhomepage"));
	}

	@Test
	@WithMockUserInformation
	public void deleteExistingUser() throws Exception {
		log.info("Loggedin User name is {}", currentUser);
		Mockito.when(authenticationUserDetailsService.deleteAuthUser("101", currentUser)).thenReturn(1);
		Map<String, Object> response = jsonMapper
				.readValue(
						mockMvc.perform(MockMvcRequestBuilders.delete("/admin/{userId}/deleteAdmin", 101))
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
								.andReturn().getResponse().getContentAsString(),
						new TypeReference<Map<String, Object>>() {
						});

		Assert.assertThat(response.get(EFFECTED_ROWS_COUNT), Matchers.is(Matchers.equalTo(1)));
		Assert.assertEquals(response.get(STATUS), SUCCESS);
	}

	@Test
	@WithMockUserInformation
	public void addNewAdmin() throws Exception {
		log.info("Loggedin User name is {}", currentUser);
		AuthenticationUser authenticationUser = AuthenticationUser.builder().userId("101").userFirstName("Vinodh Kumar")
				.userLastName("Thimmisetty").applicationName("spring-security").role(Role.APPLICATION_ADMIN)
				.userEmail("vinu@test.com").userPhone("7412563987").build();

		Mockito.when(authenticationUserDetailsService.addAuthUser(authenticationUser, currentUser)).thenReturn(1);

		Map<String, Object> response = jsonMapper.readValue(mockMvc
				.perform(MockMvcRequestBuilders.post("/admin/addNewAdminOrUser").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonMapper.writeValueAsString(authenticationUser)))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn()
				.getResponse().getContentAsString(), new TypeReference<Map<String, Object>>() {
				});

		Assert.assertThat(response.get(EFFECTED_ROWS_COUNT), Matchers.is(Matchers.equalTo(1)));
		Assert.assertEquals(response.get(STATUS), SUCCESS);
	}

	@After
	public void cleanup() {

	}
}
