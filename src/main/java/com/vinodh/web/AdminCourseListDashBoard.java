package com.vinodh.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinodh.dto.Course;

@Controller
@RequestMapping("/admin")
public class AdminCourseListDashBoard {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERRORS = "VALIDATION_ERRORS";
	public static final String STATUS = "STATUS";

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/loadCourseHomePage")
	public String loadCourseHomePage(Model model) {
		model.addAttribute("course", new Course());
		return "/admin/coursesList";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listAllCourses")
	public ResponseEntity<List<Course>> listAllCourses() {
		List<Course> courses = Collections.emptyList();
		return ResponseEntity.ok(courses);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourse() {
		Course course = null;
		return ResponseEntity.ok(course);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addNewCourse")
	public ResponseEntity<Map<String, Object>> addNewCourse(@Valid @RequestBody Course course,
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
	@PostMapping(value = "{courseId}/updateCourse")
	public ResponseEntity<Map<String, Object>> updateCourse(@PathVariable("courseId") int courseId,
			@RequestBody Course course) {

		return null;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "{courseId}/deleteCourse")
	public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable("courseId") int courseId,
			@RequestBody Course course) {

		return null;
	}

}
