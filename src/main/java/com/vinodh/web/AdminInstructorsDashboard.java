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

import com.vinodh.dto.Instructor;

@Controller
@RequestMapping("/admin")
public class AdminInstructorsDashboard {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERRORS = "VALIDATION_ERRORS";
	public static final String STATUS = "STATUS";

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/loadInstructorDetailsPage")
	public String loadInstructorDetailsPage(Model model) {
		model.addAttribute("instructor", new Instructor());
		return "/admin/instructorsList";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listAllInstructors")
	public ResponseEntity<List<Instructor>> listAllInstructors() {
		List<Instructor> instructors = Collections.emptyList();
		return ResponseEntity.ok(instructors);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{instructorId}")
	public ResponseEntity<Instructor> getInstructor() {
		Instructor instructor = null;
		return ResponseEntity.ok(instructor);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addNewInstructor")
	public ResponseEntity<Map<String, Object>> addNewInstructor(@Valid @RequestBody Instructor instructor,
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
	@PostMapping(value = "{instructorId}/updateInstructor")
	public ResponseEntity<Map<String, Object>> updateInstructor(@PathVariable("instructorId") int instructorId,
			@RequestBody Instructor instructor) {

		return null;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "{courseId}/deleteInstructor")
	public ResponseEntity<Map<String, Object>> deleteInstructor(@PathVariable("instructorId") int instructorId,
			@RequestBody Instructor instructor) {

		return null;
	}

}
