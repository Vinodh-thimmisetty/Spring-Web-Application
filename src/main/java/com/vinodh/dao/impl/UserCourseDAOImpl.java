package com.vinodh.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vinodh.dao.UserCourseDAO;
import com.vinodh.entity.Course;

@Repository
public class UserCourseDAOImpl implements UserCourseDAO {

	@Override
	public List<Course> listAllCourses() {
		return Collections.emptyList();
	}

	@Override
	public Course getCourse(int courseId) {
		return null;
	}

}
