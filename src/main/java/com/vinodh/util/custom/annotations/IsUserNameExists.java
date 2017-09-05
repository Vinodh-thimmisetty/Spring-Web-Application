package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vinodh.custom.validation.UserNameExistValidator;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UserNameExistValidator.class)
public @interface IsUserNameExists {

	String message() default "Username Already Exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
