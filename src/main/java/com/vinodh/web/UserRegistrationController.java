package com.vinodh.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * //@formatter:off
 * 		1. Try to use annotation as much as possible.Make sure "commandname" in the
 * 			<form:form> is same as model attribute name.
 * 		2. Treat All Fields of Form backing object(DTO) as STRING.
 * 		3. Use of String Trim Editor to avoid saving the trailing spaces.
 * 		4. Before Submitting the Form details to Server, client side validations are must.
 * 		 5. JSR 303 server side error messages will reflect only with spring form tags.
 * 		 6. Never have a "VOID" return type for any method in Controller.
 * 		 7. Try using Utility methods from commonslang, google guava instead of lengthy code.
 * 		 8. Use single Logging framework across entire Web Application.
 * 		 9. Don't hit the jsp page directly, try to send as ModelAndView.
 * 
 * //@formatter:on  
 * 
 * @Controller This is entry point for User Registration. Lombok is used for
 *             Logging(SLF4J).
 * 
 * @GetMapping will load the registration home page. All country related
 *             information will be send to dropdown.
 * 
 * 
 * @PostMapping will used to handle the Form data sent as JSON from the Ajax
 *              Request (OR) to handle directly by form submission.
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserRegistrationController {

	private static final  String STATUS = "STATUS";
	private static final  String SUCCESS = "SUCCESS";
	private static final  String FAIL = "FAIL";
	private static final  String ERROR = "ERROR";
	private static final  String VALIDATION_ERRORS = "VALIDATION_ERRORS";

	@Autowired
	private UserRegistrationService userRegistrationService;

	/**
	 * Registration Home page
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
	 * Based on Selection of Country, LIst of State Names will be loaded from the
	 * Database
	 * 
	 * @param searchterm
	 * @return
	 */
	@GetMapping("/autoSuggestIndianStates")
	public @ResponseBody List<String> loadIndiaStates(@RequestParam("term") String searchterm) {
		return userRegistrationService.loadStateDetails(searchterm);
	}

	/**
	 * This method is used to handle the Form data as JSON. All Error Messages are
	 * grouped as a response entity or List or Map and send back to client.
	 * 
	 * This type of response doesn't help in displaying the errors at View.
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
		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : fieldErrors) {
				jsonArray.put(new JSONObject().put(fieldError.getField(), fieldError.getDefaultMessage()));
			}

		} else {
			jsonArray.put(new JSONObject().put("Redirect", "/course/courseList"));
		}
		return ResponseEntity.ok(jsonArray.toString());
	}

	/**
	 * 
	 * This is the best way to handle form data. All Form data will be mapped
	 * to @Valid annotated Model object, where JSR 303 validaton errors are
	 * captured(if any) and using BindingResult, all server side errors will be send
	 * back to view.
	 * 
	 * In view , all errors will map based on <form:errors path="##">
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
			// Save the Form Data into Database along with Generated Validation key
			// Send Registration Confirmation Link to User
			userRegistrationService.saveUserDetails(registrationForm);

			return "redirect:/course/courseList";

		}
	}

	/**
	 * This is similar to registration POST action but the ResponseEntity is treated
	 * as Map. Any API related Endpoint, we need to implement like this
	 * 
	 * 
	 * @param registrationForm
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/registrationEndpoint")
	public ResponseEntity<Map<String, Object>> handleRegistrationLikeWebserivce(
			@Valid @ModelAttribute UserRegistrationForm registrationForm, BindingResult bindingResult) {
		Map<String, Object> responseBody = new HashMap<>();
		if (bindingResult.hasErrors()) {
			responseBody.put("fieldErrors", bindingResult.getFieldErrors());
			responseBody.put(STATUS, VALIDATION_ERRORS);
		} else {
			responseBody.put(STATUS, SUCCESS);
			responseBody.put("Redirect", "/course/courseList");

		}

		return ResponseEntity.ok(responseBody);
	}

}
