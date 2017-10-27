package com.vinodh.util.custom.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.test.context.support.WithSecurityContext;

import com.vinodh.custom.validation.WithMockCustomUserSecurityContextFactory;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD,METHOD })
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockUserInformation {
	String username() default "vinu"; 
	String name() default "vinodh";
}
