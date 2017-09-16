package com.vinodh.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.vinodh.util.custom.annotations.IsDuplicateCourseName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TO Hold Course Information
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Data
@NoArgsConstructor
public class CourseDTO {

	private int courseId;
	@NotBlank
	@IsDuplicateCourseName
	private String courseName;
	@NotBlank
	private String courseDescription;
	@NotBlank
	private Date courseStartDate;
	@NotBlank
	private long coursePrice;
	@NotBlank
	private String courseTechnology;

}
