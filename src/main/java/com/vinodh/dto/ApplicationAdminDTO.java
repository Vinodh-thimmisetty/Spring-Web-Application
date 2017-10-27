package com.vinodh.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.vinodh.util.custom.annotations.PasswordMatches;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordMatches
public class ApplicationAdminDTO {
	
	private String adminId;
	@NotBlank(message = "Please enter your First Name")
	@Length(min = 2, message = "Min Allowed Length is {min}")
	private String firstName;

	@NotBlank(message = "Please enter your Last Name")
	@Length(min = 2, message = "Min Allowed Length is {min}")
	private String lastName;

	@NotBlank(message = "Please enter your Username")
	@Length(min = 8, message = "Min Allowed Length is {min}")
	//@IsAdminNameExists
	private String adminLoginName;

	@NotBlank(message = "Please enter your Password")
	@Length(min = 8, message = "Min Allowed Length is {min}")
	private String password;

	@NotBlank(message = "Please confirm your Password")
	@Length(min = 8, message = "Min Allowed Length is {min}")
	private String adminPasswordConfirm;

	@Email(message = "Please provide a valid email address.")
	@NotBlank(message = "Email is required.")
	private String email;

	@NotBlank(message = "Please enter your Phone No.")
	@Length(min = 10, max = 10, message = "Min Allowed Length is {min} and Max Allowed Length is {max}")
	private String phoneNumber;
}
