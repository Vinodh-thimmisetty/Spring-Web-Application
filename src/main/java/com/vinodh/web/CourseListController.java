package com.vinodh.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseListController {
	private static final String LIST_COURSES_VIEWNAME = "courseList";

	@GetMapping("/courseList")
	public String loadRegistration() {
		return LIST_COURSES_VIEWNAME;
	}

}
