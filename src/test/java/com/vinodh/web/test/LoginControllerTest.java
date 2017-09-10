package com.vinodh.web.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vinodh.web.LoginController;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginControllerTest {
	private static final String CUSTOM_LOGIN_SUCCESS = "customUser";

	@InjectMocks
	LoginController loginController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	@WithMockUser
	public void loginAttempts() throws Exception {
		mockMvc.perform(get("/home")).andExpect(model().attribute("greeting", "Hello welcome boss!!"))
				.andExpect(view().name("welcome")).andExpect(status().isOk());

		mockMvc.perform(get("/admin")).andExpect(model().attribute("user", "user")).andExpect(view().name("adminView"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/db")).andExpect(model().attribute("user", "user")).andExpect(view().name("dba"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/Access_Denied")).andExpect(model().attribute("user", "user"))
				.andExpect(view().name("accessDenied")).andExpect(status().isOk());

		//mockMvc.perform(get("/login")).andExpect(view().name("loginView")).andExpect(status().isOk());

		mockMvc.perform(post("/loginSuccess")).andExpect(view().name("redirect:/" + CUSTOM_LOGIN_SUCCESS))
				.andExpect(status().is3xxRedirection());

		mockMvc.perform(get("/logout")).andExpect(view().name("redirect:/login?logout"))
				.andExpect(status().is3xxRedirection());
/*
		mockMvc.perform(get("/customUser_Secure")).andExpect(model().attribute("user", "user"))
				.andExpect(view().name(CUSTOM_LOGIN_SUCCESS)).andExpect(status().isOk());

		mockMvc.perform(get("/customUser")).andExpect(model().attribute("user", "user"))
				.andExpect(view().name(CUSTOM_LOGIN_SUCCESS)).andExpect(status().isOk());

		mockMvc.perform(get("/customUser_CurrentUser")).andExpect(model().attribute("user", "user"))
				.andExpect(view().name(CUSTOM_LOGIN_SUCCESS)).andExpect(status().isOk());*/

	}

}
