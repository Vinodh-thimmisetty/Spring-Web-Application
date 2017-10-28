package com.vinodh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinodh.service.AuthenticationUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
@PreAuthorize(value = "hasRole('ROLE_SUPER_ADMIN')")
public class AuthenticationController {

	public final static String SUCCESS = "SUCCESS";
	public final static String VALIDATION_ERROR = "VALIDATION_ERROR";
	public final static String SERVER_ERROR = "SERVER_ERROR";

	@Autowired
	private AuthenticationUserDetailsService userService;

	@GetMapping(value = "/authHomePage")
	public String loadAuthenitcationHomepage(ModelMap model) {
		model.addAttribute("users", userService.listAllAuthUsers());
		log.info("Authentication Home Page");
		return "/admin/authhomepage";
	}

}
