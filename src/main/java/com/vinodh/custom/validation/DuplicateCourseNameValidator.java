package com.vinodh.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vinodh.util.custom.annotations.IsDuplicateCourseName;

public class DuplicateCourseNameValidator implements ConstraintValidator<IsDuplicateCourseName, String> {

	@Override
	public void initialize(IsDuplicateCourseName constraintAnnotation) { 
		// nothing to do
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) { 
		return false;
	}

}
