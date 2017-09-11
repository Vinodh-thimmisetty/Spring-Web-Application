package com.vinodh.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vinodh.dto.ApplicationUser;

public class AdminDetailsDashBoard {

	public static final String SUCCESS = "SUCCESS";
	public static final String VALIDATION_ERRORS = "VALIDATION_ERRORS";
	public static final String STATUS = "STATUS";

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/loadAdminDetailsPage")
	public String loadAdminDetailsPage(Model model) {
		model.addAttribute("adminsList", new ApplicationUser());
		return "/admin/adminList";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/listAllAdmins")
	public ResponseEntity<List<ApplicationUser>> listAllCourses() {
		List<ApplicationUser> admins = Collections.emptyList();
		return ResponseEntity.ok(admins);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{adminUserId}")
	public ResponseEntity<ApplicationUser> getAdmin() {
		ApplicationUser admin = null;
		return ResponseEntity.ok(admin);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addNewAdmin")
	public ResponseEntity<Map<String, Object>> addNewAdmin(@Valid @RequestBody ApplicationUser applicationUser,
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
	@PostMapping(value = "{adminUserId}/updateUser")
	public ResponseEntity<Map<String, Object>> updateAdmin(@PathVariable("adminUserId") int adminUserId,
			@RequestBody ApplicationUser applicationUser) {

		return null;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "{adminUserId}/deleteUser")
	public ResponseEntity<Map<String, Object>> deleteAdmin(@PathVariable("adminUserId") int adminUserId,
			@RequestBody ApplicationUser applicationUser) {

		return null;
	}

}
