package com.vinodh.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "VINODH", name = "INSTRUCTOR_INFORMATION")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INSTRUCTOR_ID")
	private int instructorId;
	@Column(name = "INSTRUCTOR_NAME")
	private String instructorName;
	@Column(name = "INSTRUCTOR_EMAIL")
	private String instructorEmail;
	@Column(name = "INSTRUCTOR_TECH")
	private String instructorTechnology;
	@Column(name = "INSTRUCTOR_EXP")
	private int instructorExperience;

	// ONE Instructor Can Teach MANY Courses
	@OneToMany
	@JoinColumn(name="FK_COURSE")
	private List<Course> courselist = new ArrayList<>();
}