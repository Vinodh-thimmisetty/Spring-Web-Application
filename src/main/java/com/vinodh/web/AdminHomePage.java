package com.vinodh.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomePage {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERRORS = "VALIDATION_ERRORS";
	public static final String STATUS = "STATUS";

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/loadAdminHomePage")
	public String loadHomePage(Model model) {
		// Load All Users
		model.addAttribute("users", "Load All Users......");
		// Load All Courses
		model.addAttribute("courses", "Load All Courses....");
		// Load All Instructors
		model.addAttribute("instructors", "Load All Instructors....");
		return "/admin/homepage";
	}

}
