package com.vinodh.web;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	/**
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/signup")
	public String loadRegistration(Model model) {
		// Load Any Predefined values from the Database. Like countries Dropdown etc.
		model.addAttribute("countriesList", userRegistrationService.loadCountryDetails());
		model.addAttribute("userRegistrationForm", new UserRegistrationForm());
		return "userRegistration";
	}

	/**
	 * 
	 * 
	 * @param registrationForm
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/registration")
	public ResponseEntity<String> handleRegistration(@Valid @ModelAttribute UserRegistrationForm registrationForm,
			BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		JSONArray jsonArray = new JSONArray();
		try {
			if (bindingResult.hasErrors()) {
				for (FieldError fieldError : fieldErrors) {
					jsonArray.put(new JSONObject().put(fieldError.getField(), fieldError.getDefaultMessage()));
				}

			} else {
				jsonArray.put(new JSONObject().put("Redirect", "/course/courseList"));
			}
		} catch (JSONException e) {
			log.error("Exception While handling the JSON in registration {}", e);
		}
		return ResponseEntity.ok(jsonArray.toString());
	}

	/**
	 * 
	 * 
	 * @param registrationForm
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/registration2")
	public String handleRegistration2(@Valid @ModelAttribute UserRegistrationForm registrationForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "userRegistration";
		} else {
			return "redirect: /course/courseList";

		}
	}
}
