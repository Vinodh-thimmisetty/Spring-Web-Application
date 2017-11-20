package com.vinodh.hibernate.validation.demo;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = ValidateLengthNotNull.class)
public @interface IgnoreBlank {

	// Validate Length
	long length();

	// Validate Range
	long min();
	long max();
	
	

}
