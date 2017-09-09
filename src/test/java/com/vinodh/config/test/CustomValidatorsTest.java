package com.vinodh.config.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodh.custom.validation.EmailExistsValidator;
import com.vinodh.custom.validation.TestCustomValidationFactory;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;
import com.vinodh.web.UserRegistrationController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class CustomValidatorsTest {

	private static final String VALID_EMAIL = "vinodh5052@gmail.com";
	private static final String INVALID_EMAIL = "vinodhgmail.com";

	private static final String VALID_USERNAME = "vinodh5052";
	private static final String INVALID_USERNAME = "invalid";

	@Autowired
	MockServletContext mockServletContext;

	@InjectMocks
	UserRegistrationController userRegistrationController;
	TestCustomValidationFactory customValidationFactory;

	@Mock
	UserRegistrationService userRegistrationService;

	private ObjectMapper jsonMapper;

	@Mock
	Set<ConstraintViolation<UserRegistrationForm>> constraintViolations;
	@Mock
	ConstraintViolation<UserRegistrationForm> mockViolation;

	private Validator validator;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		sampleDTO();
		LocalValidatorFactoryBean validatorFactoryBean = getCustomValidatorFactoryBean();
		validator = validatorFactoryBean.getValidator();
		mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).setValidator(validatorFactoryBean)
				.setHandlerExceptionResolvers().setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
		//@formatter:on
	}

	@Test
	public void testCustomValidation() throws Exception {
		// when(userRegistrationService.isValidEmail(anyString())).thenReturn(true);
		when(userRegistrationService.isValidEmail("InvalidEmail")).thenReturn(false);

		// Errors errors = new BeanPropertyBindingResult(sampleDTOWithErrors(),
		// "userRegistrationForm");
		// Set<ConstraintViolation<UserRegistrationForm>> constraintViolations =
		// validator.validate(sampleDTOWithErrors());
		when(userRegistrationService.isValidEmail(VALID_EMAIL)).thenReturn(true);
		when(userRegistrationService.isValidUserName(VALID_USERNAME)).thenReturn(true);
		when(userRegistrationService.isValidEmail(INVALID_EMAIL)).thenReturn(false);
		when(userRegistrationService.isValidUserName(INVALID_USERNAME)).thenReturn(false);

		mockMvc.perform(post("/user/registration2").param("firstName", "Vinodh").param("lastName", "Thimmisetty")
				.param("userName", INVALID_USERNAME).param("userPassword", "vinodh1234")
				.param("userPasswordConfirm", "vinodh1234").param("userEmail", INVALID_EMAIL).param("country", "India")
				.param("state", "Andhra Pradesh").param("gender", "Male").param("phone", "1234567890"))
				.andExpect(status().is2xxSuccessful());

		mockMvc.perform(post("/user/registration2").param("firstName", "Vinodh").param("lastName", "Thimmisetty")
				.param("userName", VALID_USERNAME).param("userPassword", "vinodh1234")
				.param("userPasswordConfirm", "vinodh1234").param("userEmail", VALID_EMAIL).param("country", "India")
				.param("state", "Andhra Pradesh").param("gender", "Male").param("phone", "1234567890"))
				.andExpect(status().is3xxRedirection());

	}

	private LocalValidatorFactoryBean getCustomValidatorFactoryBean() {

		final GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext(mockServletContext);
		final ConfigurableListableBeanFactory beanFactory = webApplicationContext.getBeanFactory();
		beanFactory.registerSingleton(EmailExistsValidator.class.getCanonicalName(), new EmailExistsValidator());
		webApplicationContext.refresh();
		// Hibernate LocalValidatorFactoryBean for catching the Constraint Violations
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setApplicationContext(webApplicationContext);
		customValidationFactory = new TestCustomValidationFactory(webApplicationContext, userRegistrationService);
		validatorFactoryBean.setConstraintValidatorFactory(customValidationFactory);
		validatorFactoryBean.setProviderClass(HibernateValidator.class);
		validatorFactoryBean.afterPropertiesSet();

		return validatorFactoryBean;
	}

	/**
	 * Sample DTO
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
	 * Sample DTO
	 * 
	 * @return
	 */
	private UserRegistrationForm sampleDTOWithErrors() {
		//@formatter:off
				return UserRegistrationForm.builder() 
						.lastName("Thimmisetty")
						.userName("vinodh5052")
						.userPassword("vinodh1234")
						.userPasswordConfirm("vinodh12s34")
						.userEmail("vinodh5052gmail.com")
						.country("India")
						.state("Andhra Pradesh")
						.gender("Male")
						.phone("1234567890")
						.build();
	   //@formatter:off
	}
}
