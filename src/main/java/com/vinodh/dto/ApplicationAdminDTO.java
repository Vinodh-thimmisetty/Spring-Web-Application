package com.vinodh.dto;
  
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationAdminDTO {
	@NotBlank(message = "First name is required.")
	private String firstName;

	@NotBlank(message = "Last name is required.")
	private String lastName;

	@Email(message = "Please provide a valid email address.")
	@NotBlank(message = "Email is required.")
	private String email;

	@NotBlank(message = "Password is required.")
	private String password;
}
