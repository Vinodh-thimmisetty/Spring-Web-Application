package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vinodh.custom.validation.EmailExistsValidator;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = EmailExistsValidator.class)
public @interface IsEmailExists {
	String message() default "Email Already Exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
