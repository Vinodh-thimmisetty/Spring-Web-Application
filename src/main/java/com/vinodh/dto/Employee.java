package com.vinodh.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

	@NotNull
	@Size(min = 5, max = 25)
	private String empName;
	@NotNull
	@Size(min = 2, max = 25)
	@Email
	private String emailId;
	@NotNull
	private long phoneNum;
	@NotNull
	private double weight;
	@NotNull
	@DateTimeFormat(iso=ISO.DATE)
	private Date dateOfBirth;

}
