package com.vinodh.openpojo.test;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

import org.junit.Test;

import com.vinodh.hibernate.validation.demo.Car;
import com.vinodh.springdocs.demo.AuthenticationExample;
import com.vinodh.springdocs.demo.DozerBeanMappingDemo;
import com.vinodh.springdocs.demo.JavaMail;

import pl.pojo.tester.api.assertion.Method;

public class CarPojoTester {

	@Test
	public void pojoTester() {
		final Class<?> testClass = Car.class;
		final Class<?>[] testDemoClasses = { testClass, AuthenticationExample.class, DozerBeanMappingDemo.class,
				JavaMail.class };

		assertPojoMethodsForAll(testDemoClasses).testing(Method.GETTER, Method.SETTER).areWellImplemented();
	}
}
