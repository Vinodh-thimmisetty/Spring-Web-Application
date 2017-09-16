/**
 * 
 */
package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vinodh.custom.validation.DuplicateCourseNameValidator;

/**
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Documented
@Retention(CLASS)
@Target(FIELD)
@Constraint(validatedBy = DuplicateCourseNameValidator.class)
public @interface IsDuplicateCourseName {
	String message() default "Course Name Already Exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
