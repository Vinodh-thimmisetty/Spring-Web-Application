package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vinodh.custom.validation.PasswordMatchValidator;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatches {
	String message() default "Passwords doesn't match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
