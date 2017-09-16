package com.vinodh.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinodh.dao.UserCourseDAO;
import com.vinodh.dto.CourseDTO;
import com.vinodh.service.UserCourseService;

/**
 * 
 * Validations are performing in Service Layer.
 * 
 * Ideally All validations has to perform in controller layer.
 * 
 * This is just a demonstration to prove that validations can be performed at
 * any layer. This better explains how Validation errors will be catch in
 * constraintviolation set which was recommended way of cating the hibernate
 * custom validations.
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Service
public class UserCourseServiceImpl implements UserCourseService {

	@Autowired
	UserCourseDAO userCourseDAO;
 

	@Override
	public List<CourseDTO> listAllCourses() {
		return userCourseDAO.listAllCourses().stream().map(x -> new DozerBeanMapper().map(x, CourseDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CourseDTO getCourse(int courseId) {
		return new DozerBeanMapper().map(userCourseDAO.getCourse(courseId), CourseDTO.class);
	}

	@Override
	public void saveNewCourse(CourseDTO course) {

	}

	@Override
	public void updateCourse(CourseDTO course) {

	}

	@Override
	public int deleteCourse(int courseId) {
		return 0;
	}

}
