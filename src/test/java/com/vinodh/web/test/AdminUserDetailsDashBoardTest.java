package com.vinodh.web.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;
import com.vinodh.web.AdminUserDetailsDashBoard;

public class AdminUserDetailsDashBoardTest {

	private static final String SUCCESS = "SUCCESS";
	private final static String STATUS = "STATUS";

	@InjectMocks
	AdminUserDetailsDashBoard adminUserDetailsDashBoard;

	@Mock
	UserRegistrationService registrationService;

	@Mock
	BindingResult bindingResult;

	private MockMvc mockMvc;
	private ObjectMapper jsonMapper;
	private UserRegistrationForm registrationForm;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// JSON Mapping Initialization
		jsonMapper = new ObjectMapper();
		registrationForm = sampleDTO();
		mockMvc = MockMvcBuilders.standaloneSetup(adminUserDetailsDashBoard).build();
	}

	@Test
	@WithMockUser
	public void loadUsersDashboard() throws Exception {

		Mockito.when(registrationService.loadAllUserDetails()).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/loadUserDetailsPage"))
				.andExpect(MockMvcResultMatchers.view().name("/admin/usersList"))
				.andExpect(MockMvcResultMatchers.model().attribute("usersList", Collections.emptyList()))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@WithMockUser
	public void loadAllUsers() throws Exception {
		Mockito.when(registrationService.loadAllUserDetails()).thenReturn(Collections.emptyList());
		List<UserRegistrationForm> usersList = jsonMapper
				.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/admin/listAllUsers"))
						.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn().getResponse()
						.getContentAsString(), new TypeReference<List<UserRegistrationForm>>() {
						});

		Assert.assertTrue(CollectionUtils.isEmpty(usersList));
	}

	@Test
	@WithMockUser
	public void loadSingleUser() throws Exception {
		Mockito.when(registrationService.loadUserDetail(Matchers.anyInt())).thenReturn(sampleDTO());

		UserRegistrationForm response = jsonMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/admin/143"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn().getResponse()
				.getContentAsString(), UserRegistrationForm.class);

		Assert.assertEquals(sampleDTO().getCountry(), response.getCountry());

	}

	@Test
	@WithMockUser
	public void addNewUser() throws Exception {
		Map<String, Object> responseEntity;

		Mockito.doNothing().when(registrationService).saveUserDetails(Matchers.any(UserRegistrationForm.class));

		responseEntity = jsonMapper.readValue(mockMvc
				.perform(MockMvcRequestBuilders.post("/admin/addNewUser").accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonMapper.writeValueAsString(registrationForm)))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn().getResponse()
				.getContentAsString(), new TypeReference<Map<String, Object>>() {
				});

		Assert.assertThat("New User is added", responseEntity.get(STATUS),
				org.hamcrest.Matchers.is(org.hamcrest.Matchers.equalTo(SUCCESS)));

		/*
		 * mockMvc.perform(MockMvcRequestBuilders.post("/admin/addNewUser").accept(
		 * MediaType.APPLICATION_JSON_UTF8)
		 * .contentType(MediaType.APPLICATION_JSON).content(jsonMapper.
		 * writeValueAsString(registrationForm))) .andDo(MockMvcResultHandlers.print())
		 * .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		 * .andExpect(MockMvcResultMatchers.model().errorCount(2));
		 */

	}

	@Test
	@WithMockUser
	public void updateuser() throws UnsupportedEncodingException, Exception {
		Map<String, Object> responseEntity;

		Mockito.doNothing().when(registrationService).updateUser(Matchers.any(UserRegistrationForm.class));

		responseEntity = jsonMapper.readValue(mockMvc
				.perform(MockMvcRequestBuilders.put("/admin/143/updateUser").accept(MediaType.APPLICATION_JSON_UTF8)
						.contentType(MediaType.APPLICATION_JSON).content(jsonMapper.writeValueAsString(sampleDTO())))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse()
				.getContentAsString(), new TypeReference<Map<String, Object>>() {
				});

		Assert.assertEquals(SUCCESS, responseEntity.get(STATUS));
		Assert.assertThat("User Details are updated", responseEntity.get(STATUS),
				org.hamcrest.Matchers.is(org.hamcrest.Matchers.equalTo(SUCCESS)));
	}

	@Test
	@WithMockUser
	public void deleteUser() throws Exception {
		Map<String, Object> responseEntity;

		Mockito.when(registrationService.deleteUser(Matchers.anyInt())).thenReturn(1);

		responseEntity = jsonMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.delete("/admin/143/deleteUser"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse()
				.getContentAsString(), new TypeReference<Map<String, Object>>() {
				});

		Assert.assertEquals(1, responseEntity.get(STATUS));
		Assert.assertThat("User is Deleted ", responseEntity.get(STATUS),
				org.hamcrest.Matchers.is(org.hamcrest.Matchers.equalTo(1)));
	}

	/**
	 * Sample DTO with No Violations
	 * 
	 * @return
	 */
	private UserRegistrationForm sampleDTO() {
		//@formatter:off
				return UserRegistrationForm.builder()
						.id(123l)
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
						.dateOfBirth(new Date())
						.emailValidationToken("12345")
						.tokenCreatedTime(new Date())
						.build();
	   //@formatter:off
	}
}
