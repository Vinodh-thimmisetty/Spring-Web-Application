package com.vinodh.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorDTO {
	private int instructorId;
	@NotBlank
	private String instructorName;
	@NotBlank
	@Email
	private String instructorEmail;
	@NotBlank
	private String instructorTechnology;
	@NotBlank
	private int instructorExperience;
	
}
