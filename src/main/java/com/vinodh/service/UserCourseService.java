package com.vinodh.service;

import java.util.List;

import com.vinodh.dto.CourseDTO;

public interface UserCourseService extends ApplicationService {

	List<CourseDTO> listAllCourses();

	CourseDTO getCourse(int courseId);

	void saveNewCourse(CourseDTO course);

	void updateCourse(CourseDTO course);

	int deleteCourse(int courseId);

}
