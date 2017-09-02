package com.vinodh.web;

import java.util.HashMap;
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

import com.vinodh.dto.Employee;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EmployeeController {

	@GetMapping(value = "/docs/loadEmployees.tsp")
	public String loadEmployee(final ModelMap modelMap) {
		modelMap.addAttribute("employee", new Employee());
		return "employee";
	}

	@PostMapping(value = "/docs/addEmployee.tsp")
	public ResponseEntity<?> addEmployee(@Valid @ModelAttribute Employee employee, BindingResult result) {
		log.info(employee.toString());
		Map<String, String> errorMessages = new HashMap<String, String>();
		if (result.hasErrors()) {
			errorMessages = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			return ResponseEntity.badRequest().body(errorMessages);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}


}
