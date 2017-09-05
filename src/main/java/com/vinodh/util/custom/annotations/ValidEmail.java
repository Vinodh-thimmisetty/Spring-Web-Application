package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vinodh.custom.validation.CustomEmailValidator;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = CustomEmailValidator.class)
public @interface ValidEmail {

	String message() default "Invalid email";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}