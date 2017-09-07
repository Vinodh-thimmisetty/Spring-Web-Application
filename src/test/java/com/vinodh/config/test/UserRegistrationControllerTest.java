package com.vinodh.config.test;

import static org.hamcrest.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.hibernate.SessionFactory;
import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodh.config.DatabaseConfig;
import com.vinodh.config.SpringWebMVCApplicationContext;
import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;
import com.vinodh.util.custom.annotations.IsEmailExists;
import com.vinodh.web.UserRegistrationController;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { SpringWebMVCApplicationContext.class, DatabaseConfig.class })
public class UserRegistrationControllerTest {

	@InjectMocks
	UserRegistrationController userRegistrationController;

	@Mock
	UserRegistrationService userRegistrationService;

	@Mock
	UserRegistrationDAO registrationDAO;

	@Autowired
	SessionFactory sessionFactory;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void loadHomePage() throws Exception {

		// Sample List
		List<String> sampleList = Arrays.asList("India", "Australia");
		// MappingJackson2HttpMessageConverter for @ResponseBody
		String sampleJson = new ObjectMapper().writeValueAsString(sampleList);
		// Sample Form Data
		//@formatter:off
		UserRegistrationForm formData = UserRegistrationForm.builder()
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
		
		BindingResult bindingResult= new BeanPropertyBindingResult(formData,"userRegistrationForm");
		//@formatter:on
		// Mock the Service Methods
		when(userRegistrationService.loadCountryDetails()).thenReturn(sampleList);
		when(userRegistrationService.loadStateDetails(anyString())).thenReturn(sampleList);
		when(registrationDAO.isValidEmail(anyString())).thenReturn(true);
		when(registrationDAO.isValidUserName(anyString())).thenReturn(true);
		//@formatter:off
	 	mockMvc.perform(get("/user/signup"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("userRegistration"))
			   .andExpect(model().attribute("userRegistrationForm", any(UserRegistrationForm.class)))
			   .andExpect(model().attribute("countriesList", sampleList));
	 	
	 	mockMvc.perform(get("/user/autoSuggestIndianStates").param("term", "India"))
	 		   .andExpect(status().isOk())
	 		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	 		   .andExpect(content().string(sampleJson));
	 	
	 	
	 	// Validations will be done before action hits the controller  
	 	mockMvc.perform(post("/user/registration")
	 					.param("firstName", "Vinodh") 
	 					.param("lastName","Thimmisetty")
	 					.param("userName","vinodh5052")
	 					.param("userPassword","vinodh1234")
	 					.param("userPasswordConfirm","vinodh1234")
	 					.param("userEmail","vinodh5052@gmail.com")
	 					.param("country","India")
	 					.param("state","Andhra Pradesh")
	 					.param("gender","Male")
	 					.param("phone","1234567890"))
	 			.andExpect(status().is2xxSuccessful())
	 			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	 	
	 	mockMvc.perform(post("/user/registration2")
					.param("firstName", "Vinodh") 
					.param("lastName","Thimmisetty")
					.param("userName","vinodh5052")
					.param("userPassword","vinodh1234")
					.param("userPasswordConfirm","vinodh1234")
					.param("userEmail","vinodh5052@gmail.com")
					.param("country","India")
					.param("state","Andhra Pradesh")
					.param("gender","Male")
					.param("phone","1234567890"))
			.andExpect(status().is3xxRedirection());
	 	
	 	// Failure Test Cases
	 	mockMvc.perform(post("/user/registration") 
				.param("userPassword","vinodh1234")
				.param("userPasswordConfirm","vinodh1234")
				.param("userEmail","vinodh5052@gmail.com")
				.param("country","India")
				.param("state","Andhra Pradesh")
				.param("gender","Male")
				.param("phone","1234567890"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	 	
	 	mockMvc.perform(post("/user/registration2") 
				.param("userPassword","vinodh1234")
				.param("userPasswordConfirm","vinodh1234")
				.param("userEmail","vinodh5052@gmail.com")
				.param("country","India")
				.param("state","Andhra Pradesh")
				.param("gender","Male")
				.param("phone","1234567890"))
		.andExpect(status().isOk())
		.andExpect(view().name("userRegistration"));
	 	 //@formatter:on
	}

}
