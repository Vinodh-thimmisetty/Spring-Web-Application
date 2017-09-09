package com.vinodh.web.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.vinodh.web.CourseListController;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockServletContext.class })
@WebAppConfiguration
@Slf4j
public class CourseListControllerTest {

	@Autowired
	MockServletContext mockServletContext;

	@InjectMocks
	CourseListController courseListController;

	@Mock
	TestCustomValidationFactory customValidationFactory;

	private MockMvc mockMvc;
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

		//@formatter:off
	 	//*****IMP*****//
		// Any Spring MVC related  components, we need to load here
		mockMvc = MockMvcBuilders
					.standaloneSetup(courseListController)
					.setValidator(validatorFactoryBean) // **** Custom Test Validation 
					.setHandlerExceptionResolvers()
					.setMessageConverters(new MappingJackson2HttpMessageConverter())
					.build();
				//@formatter:on 

	}

	@Test
	public void courseListControllerSuccessTestCases() throws Exception {
		mockMvc.perform(get("/course/courseList")).andExpect(status().isOk()).andExpect(view().name("coursesList"));
		log.info("CourseList Get Action Test case is passed");
	}

	private LocalValidatorFactoryBean getCustomValidatorFactoryBean() {

		final GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext(mockServletContext);
		final ConfigurableListableBeanFactory beanFactory = webApplicationContext.getBeanFactory();
		beanFactory.registerSingleton(EmailExistsValidator.class.getCanonicalName(), new EmailExistsValidator());
		webApplicationContext.refresh();

		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setApplicationContext(webApplicationContext);
		customValidationFactory = new TestCustomValidationFactory(webApplicationContext, null);
		validatorFactoryBean.setConstraintValidatorFactory(customValidationFactory);
		validatorFactoryBean.setProviderClass(HibernateValidator.class);
		validatorFactoryBean.afterPropertiesSet();

		return validatorFactoryBean;
	}
}
