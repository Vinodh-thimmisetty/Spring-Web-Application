package com.vinodh.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationUser {
	@NotEmpty(message = "First name is required.")
	private String firstName;

	@NotEmpty(message = "Last name is required.")
	private String lastName;

	@Email(message = "Please provide a valid email address.")
	@NotEmpty(message = "Email is required.")
	private String email;

	@NotEmpty(message = "Password is required.")
	private String password;
}
