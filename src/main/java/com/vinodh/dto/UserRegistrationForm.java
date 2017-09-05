package com.vinodh.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.vinodh.util.custom.annotations.IsEmailExists;
import com.vinodh.util.custom.annotations.IsUserNameExists;
import com.vinodh.util.custom.annotations.PasswordMatches;
import com.vinodh.util.custom.annotations.ValidEmail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordMatches
public class UserRegistrationForm {

	@NotEmpty(message = "Please enter your Username")
	@Length(min = 8, message = "Min Allowed Length is {2}")
	@IsUserNameExists
	private String userName;
	@NotEmpty(message = "Please enter your Password")
	@Length(min = 8, message = "Min Allowed Length is {2}")
	private String userPassword;
	@NotEmpty(message = "Please confirm your Password")
	@Length(min = 8, message = "Min Allowed Length is {2}")
	private String userPasswordConfirm;
	@NotEmpty(message = "Please enter your Email Address")
	@Length(min = 8, message = "Min Allowed Length is {2}")
	@ValidEmail
	@IsEmailExists
	private String userEmail;
	@NotEmpty(message = "Please enter your First Name")
	@Length(min = 2, message = "Min Allowed Length is {2}")
	private String firstName;
	@NotEmpty(message = "Please enter your Last Name")
	@Length(min = 2, message = "Min Allowed Length is {2}")
	private String lastName;
	@NotEmpty(message = "Please select your country")
	@Length(min = 3, message = "Min Allowed Length is {2}")
	private String country;
	@NotEmpty(message = "Please select your State")
	@Length(min = 3, message = "Min Allowed Length is {2}")
	private String state;
	@NotEmpty(message = "Please enter your Phone No.")
	@Length(min = 10, max = 10, message = "Min Allowed Length is {2}")
	private String phone;
	@NotEmpty(message = "Please enter your Gender")
	private String gender;
}
