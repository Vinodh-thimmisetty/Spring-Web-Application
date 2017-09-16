package com.vinodh.web;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vinodh.dto.EmployeeDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeController {
	private static final String EMPLOYEE_VIEW_NAME = "employee";

	@GetMapping(value = "/docs/loadEmployees.tsp")
	public String loadEmployee(final ModelMap modelMap) {
		modelMap.addAttribute("employee", new EmployeeDTO());
		return EMPLOYEE_VIEW_NAME;
	}

	@PostMapping(value = "/docs/addEmployeeAjax.tsp")
	public ResponseEntity<Map<String, String>> addEmployeeAjax(@Valid @ModelAttribute EmployeeDTO employee,
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
	public String addEmployee(@Valid @ModelAttribute EmployeeDTO employee, BindingResult result) {
		log.info(employee.toString());

		if (result.hasErrors()) {
			return EMPLOYEE_VIEW_NAME;
		} else {
			return "Done";
		}

	}

}
