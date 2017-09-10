package com.vinodh.dto;

import java.util.Date;

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
public class Course {

	private int courseId;
	private String courseName;
	private String courseDescription;
	private Date courseStartDate;
	private long coursePrice;
	private String courseTechnology;
	private Instructor courseInstructor;

}
