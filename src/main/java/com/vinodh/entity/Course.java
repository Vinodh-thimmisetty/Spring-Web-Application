package com.vinodh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQuery;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "VINODH", name = "COURSE_INFORMATION")
@NamedQuery(name = "load.allCourses", query = "from Course")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COURSE_ID")
	private int courseId;
	@Column(name = "COURSE_NAME")
	private String courseName;
	@Column(name = "COURSE_DESCRIPTION")
	private String courseDescription;
	@Column(name = "COURSE_START_DATE")
	@Temporal(TemporalType.DATE)
	private Date courseStartDate;
	@Column(name = "COURSE_PRICE")
	private long coursePrice;
	@Column(name = "COURSE_TECHNOLOGY")
	private String courseTechnology;

	/**
	 * ONE Course will be Mapped to exactly ONE Instructor, but Instructor Can take
	 * MANY Courses
	 */
	@ManyToOne
	@Transient
	private Instructor courseInstructor;

}
