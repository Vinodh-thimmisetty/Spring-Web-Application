package com.vinodh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinodh.domain.AuthenticationUser;
import com.vinodh.service.AuthenticationUserDetailsService;

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

	public static final  String SUCCESS = "SUCCESS";
	public static final  String VALIDATION_ERROR = "VALIDATION_ERROR";
	public static final  String SERVER_ERROR = "SERVER_ERROR";

	@Autowired
	private AuthenticationUserDetailsService userService;

	@GetMapping(value = "/authHomePage")
	public String loadAuthenitcationHomepage(ModelMap model) {
		model.addAttribute("users", userService.listAllAuthUsers());
		model.addAttribute("authorizedAdmin", new AuthenticationUser());
		log.info("Authentication Home Page");
		return "/admin/authhomepage";
	}

}
