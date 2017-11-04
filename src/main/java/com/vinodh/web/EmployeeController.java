package com.vinodh.web;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vinodh.dto.EmployeeDTO;
import com.vinodh.dto.EmployeeDTO.Group1;
import com.vinodh.dto.EmployeeDTO.Group2;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeController {
	private static final String EMPLOYEE_VIEW_NAME = "employee";

	 
	@GetMapping(value = "/docs/loadEmployees.tsp")
	public String loadEmployee(final ModelMap modelMap) {
		modelMap.addAttribute("employeeDTO", new EmployeeDTO());
		return EMPLOYEE_VIEW_NAME;
	}

	@PostMapping(value = "/docs/addEmployeeAjax.tsp")
	public ResponseEntity<Map<String, String>> addEmployeeAjax(@Validated(Group1.class) @ModelAttribute EmployeeDTO employee,
			BindingResult result) {
		log.info(employee.toString());

		if (result.hasErrors()) {
			Map<String, String> errorMessages = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			return ResponseEntity.badRequest().body(errorMessages);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PostMapping(value = "/docs/addEmployee.tsp")
	public String addEmployee(@Validated(Group1.class) @ModelAttribute EmployeeDTO employee, BindingResult result) {
		log.info(employee.toString());

		if (result.hasErrors()) {
			return EMPLOYEE_VIEW_NAME;
		} else {
			return "Done";
		}

	}
	
	@PostMapping(value = "/docs/addEmployee2.tsp")
	public String addEmployee2(@Validated(Group2.class) @ModelAttribute EmployeeDTO employee, BindingResult result) {
		log.info(employee.toString());

		if (result.hasErrors()) {
			return EMPLOYEE_VIEW_NAME;
		} else {
			return "Done";
		}

	}

}
