package com.vinodh.dao;

import java.util.List;

import com.vinodh.entity.Course;
import com.vinodh.service.ApplicationService;

public interface UserCourseDAO extends ApplicationService {

	List<Course> listAllCourses();

	Course getCourse(int courseId);

}
