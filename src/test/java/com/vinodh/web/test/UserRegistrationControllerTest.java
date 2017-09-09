package com.vinodh.web.test;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodh.custom.validation.EmailExistsValidator;
import com.vinodh.custom.validation.TestCustomValidationFactory;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;
import com.vinodh.web.UserRegistrationController;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Mock Servlet Context to by pass Spring Web Application Context.
 * 
 * Create a custom Spring Web Application context to Validate the Custom
 * Validation Constraints.
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockServletContext.class })
@WebAppConfiguration
@Slf4j
public class UserRegistrationControllerTest {

	private static final String VALID_EMAIL = "vinodh5052@gmail.com";
	private static final String INVALID_EMAIL = "vinodhgmail.com";

	private static final String VALID_USERNAME = "vinodh5052";
	private static final String INVALID_USERNAME = "invalid";

	private static final String SUCCESS = "SUCCESS";
	private static final String VALIDATION_ERRORS = "VALIDATION_ERRORS";
	private final static String STATUS = "STATUS";

	@Autowired
	MockServletContext mockServletContext;

	@InjectMocks
	UserRegistrationController userRegistrationController;

	@Mock
	TestCustomValidationFactory customValidationFactory;

	@Mock
	UserRegistrationService userRegistrationService;

	private MockMvc mockMvc;
	private UserRegistrationForm formData;
	private ObjectMapper jsonMapper;

	/**
	 * 
	 * Common Initialization Method.
	 * 
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// JSON Mapping Initialization
		jsonMapper = new ObjectMapper();

		// Load the Custom Hibernate Validator instance
		LocalValidatorFactoryBean validatorFactoryBean = getCustomValidatorFactoryBean();

		formData = sampleDTO();
		BindingResult bindingResult = new BeanPropertyBindingResult(formData, "userRegistrationForm");

		//@formatter:off
	 	//*****IMP*****//
		// Any Spring MVC related  components, we need to load here
		mockMvc = MockMvcBuilders
					.standaloneSetup(userRegistrationController)
					.setValidator(validatorFactoryBean) // **** Custom Test Validation 
					.setHandlerExceptionResolvers()
					.setMessageConverters(new MappingJackson2HttpMessageConverter())
					.build();
				//@formatter:on 

	}

	/**
	 * 
	 * Test case for All the Successful Actions in User Registration Controller.
	 * Here Success doesn't mean violating the constraint, if there is any
	 * constraint violations, corresponding action will be taken. For e.g. If there
	 * is any constraint violation while posting the form data
	 * "/user/registration2", Binding result will throw errors back to the same page
	 * with redirecting.
	 * 
	 * @throws Exception
	 */
	@Test
	public void registrationControllerSuccessTestCases() throws Exception {
		Map<String, Object> responseEntity;

		// Sample List
		List<String> sampleList = Arrays.asList("India", "Australia");
		// MappingJackson2HttpMessageConverter for @ResponseBody
		String sampleJson = new ObjectMapper().writeValueAsString(sampleList);

		//@formatter:off  
		
		// Invalid Action URL Invoked
		mockMvc.perform(get("/user/Unknown"))
		   		.andExpect(status().isNotFound());
	
		// Mock the Service Methods
		when(userRegistrationService.loadCountryDetails()).thenReturn(sampleList);
		when(userRegistrationService.loadStateDetails(anyString())).thenReturn(sampleList);
		
 	 	mockMvc.perform(get("/user/signup"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("userRegistration"))
			   .andExpect(model().attribute("userRegistrationForm", any(UserRegistrationForm.class)))
			   .andExpect(model().attribute("countriesList", sampleList));
	 	
 	 	log.info("Signing up Action test cases are passed");
	 	mockMvc.perform(get("/user/autoSuggestIndianStates").param("term", "India"))
	 		   .andExpect(status().isOk())
	 		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	 		   .andExpect(content().string(sampleJson));
	 	log.info("autoSuggestIndianStates Action test cases are passed");
	 	
	 	// Validations will be done before action hits the controller 
	 	// Mocking Custom Constraint Validation
	 	when(userRegistrationService.isValidEmail(VALID_EMAIL)).thenReturn(true);
	 	when(userRegistrationService.isValidUserName(VALID_USERNAME)).thenReturn(true); 
		when(userRegistrationService.isValidEmail(INVALID_EMAIL)).thenReturn(false);
	 	when(userRegistrationService.isValidUserName(INVALID_USERNAME)).thenReturn(false); 
		 
					 	mockMvc.perform(post("/user/registration2")
									.param("firstName", "Vinodh") 
									.param("lastName","Thimmisetty")
									.param("userName",VALID_USERNAME)
									.param("userPassword","vinodh1234")
									.param("userPasswordConfirm","vinodh1234")
									.param("userEmail",VALID_EMAIL)
									.param("country","India")
									.param("state","Andhra Pradesh")
									.param("gender","Male")
									.param("phone","1234567890"))
					 			.andExpect(status().is3xxRedirection());
			 log.info("registration2/ with all Valid form fields test cases are completed");	
			
			 			mockMvc.perform(post("/user/registration2") 
									.param("userPassword","vinodh1234")
									.param("userPasswordConfirm","vinodh1234")
									.param("userName",INVALID_USERNAME)
									.param("userEmail",INVALID_EMAIL)
									.param("country","India")
									.param("state","Andhra Pradesh")
									.param("gender","Male")
									.param("phone","1234567890"))
						 			.andExpect(status().isOk())
					 			.andExpect(view().name("userRegistration"));
			log.info("registration2/ with InValid form fields test cases are completed");
			
				String jsonResponse = "\"[{\\\"Redirect\\\":\\\"/course/courseList\\\"}]\"";
				 	   mockMvc.perform(post("/user/registration")
				 					.param("firstName", "Vinodh") 
				 					.param("lastName","Thimmisetty")
				 					.param("userName",VALID_USERNAME)
				 					.param("userPassword","vinodh1234")
				 					.param("userPasswordConfirm","vinodh1234")
				 					.param("userEmail",VALID_EMAIL)
				 					.param("country","India")
				 					.param("state","Andhra Pradesh")
				 					.param("gender","Male")
				 					.param("phone","1234567890"))
					  		 .andExpect(status().is2xxSuccessful())
					  		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					  		 .andExpect(content().string(jsonResponse));
		log.info("registration/ with all Valid form fields test cases are passed");
		
					   mockMvc.perform(post("/user/registration") 
							   		.param("firstName", "Vinodh") 
							   		.param("lastName","Thimmisetty") 
				 			 		.param("userPassword","vinodh1234")
									.param("userPasswordConfirm","vinodh1234")
									.param("userName",INVALID_USERNAME)
									.param("userEmail",INVALID_EMAIL)
									.param("country","India")
									.param("state","Andhra Pradesh")
									.param("gender","Male")
									.param("phone","1234567890"))
							.andExpect(status().isOk()) 
							.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
							.andExpect(content().string(containsString("Email Already Exists")))
							.andExpect(content().string(containsString("Username Already Exists")))
							.andExpect(content().string(containsString("Invalid email")))
							.andExpect(content().string(containsString("Min Allowed Length is 8")));
					   
			log.info("registration/ with InValid form fields test cases are completed");
						
						
	  responseEntity =jsonMapper.readValue(
			  			mockMvc.perform(post("/user/registrationEndpoint")
				 					.param("firstName", "Vinodh") 
				 					.param("lastName","Thimmisetty")
				 					.param("userName",VALID_USERNAME)
				 					.param("userPassword","vinodh1234")
				 					.param("userPasswordConfirm","vinodh1234")
				 					.param("userEmail",VALID_EMAIL)
				 					.param("country","India")
				 					.param("state","Andhra Pradesh")
				 					.param("gender","Male")
				 					.param("phone","1234567890"))
					  		 .andExpect(status().is2xxSuccessful())
					  		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					  		 .andReturn().getResponse().getContentAsString(), new TypeReference<Map<String,Object>>(){});
	  log.info("registrationEndpoint/ with all Valid form fields test cases are passed");
		
	  assertThat(responseEntity.get(STATUS), is(equalTo(SUCCESS)));	 	
	  
	  
	 responseEntity =jsonMapper.readValue(
			 			mockMvc.perform(post("/user/registrationEndpoint") 
									.param("userPassword","vinodh1234")
									.param("userPasswordConfirm","vinodh1234")
									.param("userName",INVALID_USERNAME)
									.param("userEmail",INVALID_EMAIL)
									.param("country","India")
									.param("state","Andhra Pradesh")
									.param("gender","Male")
									.param("phone","1234567890"))
							.andExpect(status().isOk())
							.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
							.andReturn().getResponse().getContentAsString(), new TypeReference<Map<String,Object>>(){}); 
	 log.info("registrationEndpoint/ with InValid form fields test cases are completed");
				
		assertThat(responseEntity.get(STATUS), is(equalTo(VALIDATION_ERRORS)));	 
		 		 	
	 	
	 	
	 	 //@formatter:on
	}

	private LocalValidatorFactoryBean getCustomValidatorFactoryBean() {

		final GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext(mockServletContext);
		final ConfigurableListableBeanFactory beanFactory = webApplicationContext.getBeanFactory();
		beanFactory.registerSingleton(EmailExistsValidator.class.getCanonicalName(), new EmailExistsValidator());
		webApplicationContext.refresh();

		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setApplicationContext(webApplicationContext);
		customValidationFactory = new TestCustomValidationFactory(webApplicationContext, userRegistrationService);
		validatorFactoryBean.setConstraintValidatorFactory(customValidationFactory);
		validatorFactoryBean.setProviderClass(HibernateValidator.class);
		validatorFactoryBean.afterPropertiesSet();

		return validatorFactoryBean;
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
	 * Sample DTO with Inbuilt Constraint Violations
	 * 
	 * 1. First Name is NULL
	 * 2. User Name Length
	 * 3. Invalid Email Format
	 * 4. Date of Birth is in Future
	 * 
	 * 
	 * @return
	 */
	private UserRegistrationForm sampleDTOWithInBuiltConstraints() {
		//@formatter:off
				return UserRegistrationForm.builder() 
						.lastName("Th")
						.userName("vinodh5052")
						.userPassword("vinodh1234")
						.userPasswordConfirm("vinodh1234")
						.userEmail("vinodh5052@gmail.com")
						.country("India")
						.state("Andhra Pradesh")
						.gender("Male")
						.phone("1234567890")
						.dateOfBirth(Date.valueOf(LocalDate.of(2020, 11, 11)))
						.build();
	   //@formatter:off
	}
	

	/**
	 * Sample DTO with Inbuilt Constraint Violations
	 * 
	 * 1. Email Format Invalid
	 * 2. Passwords doesn't match
	 * 3. UserName already Exists 
	 * 
	 * @return
	 */
	private UserRegistrationForm sampleDTOWithCustomConstraints() {
		//@formatter:off
		return UserRegistrationForm.builder()
				.firstName("Vinodh")
				.lastName("Thimmisetty")
				.userName("vinodh5052")
				.userPassword("vinosdh1234")
				.userPasswordConfirm("vinodh1234")
				.userEmail("vinodhgmail.com")
				.country("India")
				.state("Andhra Pradesh")
				.gender("Male")
				.phone("1234567890")
				.build();
	   //@formatter:off
	}

}
