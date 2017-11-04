package com.vinodh.web.test;

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vinodh.dto.EmployeeDTO;
import com.vinodh.web.EmployeeController;

public class EmployeeControllerTest {
	private static final String EMPLOYEE_VIEW_NAME = "employee";

	@InjectMocks
	EmployeeController employeeController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void loadEmployee() throws Exception {
		mockMvc.perform(get("/docs/loadEmployees.tsp"))
				.andExpect(model().attribute("employeeDTO", any(EmployeeDTO.class)))
				.andExpect(view().name(EMPLOYEE_VIEW_NAME)).andExpect(status().isOk());
	}

	@Test
	public void addEmployee() throws Exception {
		mockMvc.perform(post("/docs/addEmployee.tsp").param("empName", "Vinodh")).andExpect(status().isOk())
				.andExpect(view().name(EMPLOYEE_VIEW_NAME));
		mockMvc.perform(post("/docs/addEmployee.tsp").param("empName", "Vinodhkumar")
				.param("emailId", "Vinodh@gmail.com").param("phoneNum", "1234567897").param("weight", "70")
				.param("dateOfBirth", LocalDate.now().toString())).andExpect(status().isOk())
				.andExpect(view().name("Done"));

		mockMvc.perform(post("/docs/addEmployeeAjax.tsp").param("empName", "Vinodh"))
				.andExpect(status().isBadRequest());
		mockMvc.perform(post("/docs/addEmployeeAjax.tsp").param("empName", "Vinodhkumar")
				.param("emailId", "Vinodh@gmail.com").param("phoneNum", "1234567897").param("weight", "70")
				.param("dateOfBirth", LocalDate.now().toString())).andExpect(status().isCreated());

	}

}
