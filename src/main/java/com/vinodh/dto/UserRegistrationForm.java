package com.vinodh.dto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.vinodh.util.custom.annotations.IsEmailExists;
import com.vinodh.util.custom.annotations.IsUserNameExists;
import com.vinodh.util.custom.annotations.PasswordMatches;
import com.vinodh.util.custom.annotations.ValidEmail;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
@Slf4j
@Builder
public class UserRegistrationForm {

	private static final String DEFAULT_SECURE_RANDOM_NUMBER = "192837433vinodh4165392087";

	@NotBlank(message = "Please enter your Username")
	@Length(min = 8, message = "Min Allowed Length is {min}")
	@IsUserNameExists
	private String userName;
	@NotBlank(message = "Please enter your Password")
	@Length(min = 8, message = "Min Allowed Length is {min}")
	private String userPassword;
	@NotBlank(message = "Please confirm your Password")
	@Length(min = 8, message = "Min Allowed Length is {min}")
	private String userPasswordConfirm;
	@NotBlank(message = "Please enter your Email Address")
	@Length(min = 8, message = "Min Allowed Length is {min}")
	@ValidEmail
	@IsEmailExists
	private String userEmail;
	@NotBlank(message = "Please enter your First Name")
	@Length(min = 2, message = "Min Allowed Length is {min}")
	private String firstName;
	@NotBlank(message = "Please enter your Last Name")
	@Length(min = 2, message = "Min Allowed Length is {min}")
	private String lastName;
	@NotBlank(message = "Please select your country")
	@Length(min = 3, message = "Min Allowed Length is {min}")
	private String country;
	@NotBlank(message = "Please select your State")
	@Length(min = 3, message = "Min Allowed Length is {min}")
	private String state;
	@NotBlank(message = "Please enter your Phone No.")
	@Length(min = 10, max = 10, message = "Min Allowed Length is {min} and Max Allowed Length is {max}")
	private String phone;
	@NotBlank(message = "Please enter your Gender")
	private String gender;
	@Past
	private Date dateOfBirth;
	// Generate a secure Random Number to hold the verification token
	// In case of any Exception, default random number is assigned
	@Getter(AccessLevel.NONE)
	private String emailValidationToken;

	public String getEmailValidationToken() {
		return StringUtils.defaultIfBlank(String.valueOf(generateSecureRandomNumber()), DEFAULT_SECURE_RANDOM_NUMBER)
				+ getUserName();

	}
	
	@Builder.Default
	private Date tokenCreatedTime = new Date();

	private Long generateSecureRandomNumber() {
		try {
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			return secureRandom.nextLong();
		} catch (NoSuchAlgorithmException e) {
			log.error("Exception occurred while generation Algorithm ", e);
		}
		return null;
	}
}
