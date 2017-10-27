package com.vinodh.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.vinodh.dto.CourseDTO;
import com.vinodh.service.UserCourseService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminCourseListDashBoard {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERRORS = "VALIDATION_ERRORS";
	public static final String STATUS = "STATUS";

	@Autowired
	UserCourseService usercourseService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/loadCourseHomePage")
	public String loadCourseHomePage(Model model) {
		log.info("Load Course Homepage");
		model.addAttribute("course", new CourseDTO());
		return "/admin/coursesList";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listAllCourses")
	public ResponseEntity<List<CourseDTO>> listAllCourses() {
		log.info("List all Courses");
		List<CourseDTO> courses = usercourseService.listAllCourses();
		return ResponseEntity.ok(courses);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/course/{courseId}")
	public ResponseEntity<CourseDTO> getCourse(@PathVariable("courseId") int courseId) {
		log.info("Get a Course of {}", courseId);
		CourseDTO course = usercourseService.getCourse(courseId);
		return ResponseEntity.ok(course);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addNewCourse")
	public ResponseEntity<Map<String, Object>> addNewCourse(@RequestBody CourseDTO course) {
		log.info("Add a new Course");
		Map<String, Object> fieldErrors = Collections.emptyMap();
		usercourseService.saveNewCourse(course);
		fieldErrors.put(STATUS, SUCCESS);
		return ResponseEntity.ok(fieldErrors);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "{courseId}/updateCourse")
	public ResponseEntity<Map<String, Object>> updateCourse(@PathVariable("courseId") int courseId,
			@RequestBody CourseDTO course) {
		log.info("Update a Course of {}", courseId);
		course.setCourseId(courseId);
		Map<String, Object> responseBody = new HashMap<>();
		usercourseService.updateCourse(course);
		responseBody.put(STATUS, SUCCESS);
		return ResponseEntity.ok(responseBody);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "{courseId}/deleteCourse")
	public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable("courseId") int courseId) {
		log.info("Delete a Course of {}", courseId);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(STATUS, usercourseService.deleteCourse(courseId));
		return ResponseEntity.ok(responseBody);
	}

}
