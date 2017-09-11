package com.vinodh.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vinodh.dto.ApplicationUser;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;

public class AdminUserDetailsDashBoard {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERRORS = "VALIDATION_ERRORS";
	public static final String STATUS = "STATUS";

	@Autowired
	UserRegistrationService userRegistrationService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/loadUserDetailsPage")
	public String loadUserDetailsPage(Model model) {
		// Get All Users List
		List<UserRegistrationForm> usersList = userRegistrationService.loadAllUserDetails();
		model.addAttribute("usersList", usersList);
		return "/admin/usersList";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listAllUsers")
	public ResponseEntity<List<ApplicationUser>> listAllUsers() {
		List<ApplicationUser> users = Collections.emptyList();
		return ResponseEntity.ok(users);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{userId}")
	public ResponseEntity<ApplicationUser> getUser() {
		ApplicationUser user = null;
		return ResponseEntity.ok(user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addNewUser")
	public ResponseEntity<Map<String, Object>> addNewUser(@Valid @RequestBody ApplicationUser applicationUser,
			BindingResult bindingResult) {
		Map<String, Object> fieldErrors = Collections.emptyMap();
		if (bindingResult.hasFieldErrors()) {
			fieldErrors.put(STATUS, VALIDATION_ERRORS);
		} else {

			fieldErrors.put(STATUS, SUCCESS);
		}
		return ResponseEntity.ok(fieldErrors);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "{userId}/updateUser")
	public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("userId") int userId,
			@RequestBody ApplicationUser applicationUser) {

		return null;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "{userId}/deleteUser")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("userId") int userId,
			@RequestBody ApplicationUser applicationUser) {

		return null;
	}

}
