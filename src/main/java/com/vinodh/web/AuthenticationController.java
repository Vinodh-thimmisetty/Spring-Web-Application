package com.vinodh.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinodh.domain.AuthenticationUser;
import com.vinodh.service.AuthenticationUserDetailsService;
import com.vinodh.service.UserRegistrationService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Accessible only to SUPER ADMIN who will manage the Application specific
 * Admins who will monitor the applications.
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Controller
@RequestMapping("/admin")
@Slf4j
@PreAuthorize(value = "hasRole('ROLE_SUPER_ADMIN')")
public class AuthenticationController {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
	public static final String SERVER_ERROR = "SERVER_ERROR";
	public static final String STATUS = "STATUS";
	public static final String EFFECTED_ROWS_COUNT = "COUNT";

	@Autowired
	private AuthenticationUserDetailsService authenticationUserDetailsService;

	@Autowired
	private UserRegistrationService userRegistrationService;

	@GetMapping(value = "/authHomePage")
	public String loadAuthenitcationHomepage(ModelMap model) {
		model.addAttribute("users", authenticationUserDetailsService.listAllAuthUsers());
		model.addAttribute("registeredUsers", userRegistrationService.loadAllUserDetails());
		model.addAttribute("authorizedAdmin", new AuthenticationUser());
		log.info("Authentication Home Page");
		return "/admin/authhomepage";
	}

	@DeleteMapping(value = "{userId}/deleteAdmin")
	public ResponseEntity<Map<String, Object>> deleteAdmin(@PathVariable("userId") String userId) {
		log.info("Delete a userId of {}", userId);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(EFFECTED_ROWS_COUNT,
				authenticationUserDetailsService.deleteAuthUser(userId, loggedInUserNameFromSpringSecurityContext()));
		responseBody.put(STATUS, SUCCESS);
		return ResponseEntity.ok(responseBody);
	}

	/**
	 * Validations will be performed at Service Layer
	 * 
	 * @param authenticationUser
	 * @return
	 */
	@PostMapping(value = "/addNewAdmin")
	public ResponseEntity<Map<String, Object>> addNewAdmin(@RequestBody AuthenticationUser authenticationUser) {
		log.info("Adding a new User");
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(EFFECTED_ROWS_COUNT, authenticationUserDetailsService.addAuthUser(authenticationUser,
				loggedInUserNameFromSpringSecurityContext()));
		responseBody.put(STATUS, SUCCESS);
		return ResponseEntity.ok(responseBody);
	}

	/**
	 * Avoid static calls as much as possible
	 * 
	 * @return
	 */
	private String loggedInUserNameFromSpringSecurityContext() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
