package com.vinodh.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.service.UserRegistrationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
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
	public ResponseEntity<List<UserRegistrationForm>> listAllUsers() {
		List<UserRegistrationForm> users = userRegistrationService.loadAllUserDetails();
		return ResponseEntity.ok(users);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{userId}")
	public ResponseEntity<UserRegistrationForm> getUser(@PathVariable("userId") int userId) {
		return ResponseEntity.ok(userRegistrationService.loadUserDetail(userId));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addNewUser")
	public ResponseEntity<Map<String, Object>> addNewUser(@RequestBody UserRegistrationForm user)
			throws MessagingException {
		Map<String, Object> fieldErrors = new HashMap<>();
		userRegistrationService.saveUserDetails(user);
		fieldErrors.put(STATUS, SUCCESS);
		return ResponseEntity.ok(fieldErrors);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "{userId}/updateUser")
	public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("userId") int userId,
			@RequestBody UserRegistrationForm user) {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(STATUS, userRegistrationService.updateUser(user));
		return ResponseEntity.ok(responseBody);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "{userId}/deleteUser")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("userId") int userId) {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(STATUS, userRegistrationService.deleteUser(userId));
		return ResponseEntity.ok(responseBody);
	}

}
