package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.constraints.NotNull;

import com.vinodh.custom.validation.EmployeeNameValidator;

@NotNull
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=EmployeeNameValidator.class)
public @interface ValidEmpName {

}
