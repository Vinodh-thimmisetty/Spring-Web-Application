package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.vinodh.custom.validation.UserIDValidator;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=UserIDValidator.class)
public @interface IsValidUser {

}
