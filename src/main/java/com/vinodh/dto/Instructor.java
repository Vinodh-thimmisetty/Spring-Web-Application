package com.vinodh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Instructor {
	private int instructorId;
	private String instructorName;
	private String instructorEmail;
	private String instructorTechnology;
	private int instructorExperience;
}
