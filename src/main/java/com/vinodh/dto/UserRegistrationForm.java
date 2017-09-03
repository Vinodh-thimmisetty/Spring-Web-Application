package com.vinodh.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationForm {

	@NotEmpty(message = "Please enter your Username")
	@Length(min = 8,message="Min Allowed Length is {1}")
	private String userName;
	@NotEmpty(message = "Please enter your Password")
	@Length(min = 8,message="Min Allowed Length is {1}")
	private String userPassword;
	@NotEmpty(message = "Please confirm your Password")
	@Length(min = 8,message="Min Allowed Length is {1}")
	private String userPasswordConfirm;
	@NotEmpty(message = "Please enter your Email Address")
	@Length(min = 8,message="Min Allowed Length is {1}")
	@Email(message = "Please enter a valid Email Address")
	private String userEmail;
	@NotEmpty(message = "Please enter your First Name")
	@Length(min = 2,message="Min Allowed Length is {1}")
	private String firstName;
	@NotEmpty(message = "Please enter your Last Name")
	@Length(min = 2,message="Min Allowed Length is {1}")
	private String lastName;
	@NotEmpty(message = "Please select your country")
	@Length(min = 3,message="Min Allowed Length is {1}")
	private String country;
	@NotNull(message = "Please enter your Phone No.")
	private Long phone;
}
