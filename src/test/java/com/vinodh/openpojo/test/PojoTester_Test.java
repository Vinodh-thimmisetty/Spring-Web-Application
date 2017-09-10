package com.vinodh.openpojo.test;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

import org.junit.Test;

import com.vinodh.dto.ApplicationUser;
import com.vinodh.dto.Course;
import com.vinodh.dto.Employee;
import com.vinodh.dto.Instructor;
import com.vinodh.dto.Mail;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.entity.Country;
import com.vinodh.entity.State;
import com.vinodh.hibernate.validation.demo.Car;

import pl.pojo.tester.api.PackageFilter;
import pl.pojo.tester.api.assertion.Method;

public class PojoTester_Test {
	public static final String PACKAGE_NAME = "com.vinodh.dto";
	PackageFilter packageFilter = new PackageFilter() {
		@Override
		public Class<?>[] getClasses() {
			return new Class[] { UserRegistrationForm.class, Course.class, Employee.class, Instructor.class,
					Mail.class, ApplicationUser.class };
		}
	};

	@Test
	public void pojoTester() {
		final Class<?>[] testDTOClass = { UserRegistrationForm.class, Course.class, Employee.class, Instructor.class,
				Mail.class, ApplicationUser.class };

		final Class<?>[] testEntityClass = { com.vinodh.entity.ApplicationUser.class, Country.class, State.class,
				com.vinodh.entity.Employee.class };

		

		//  assertPojoMethodsForAll(testDTOClass);
		 // assertPojoMethodsForAll(testEntityClass);
		// assertPojoMethodsForAll(packageFilter);
		 assertPojoMethodsForAll(testDTOClass)
				.testing(Method.GETTER, Method.SETTER, Method.TO_STRING, Method.EQUALS, Method.HASH_CODE)
				.areWellImplemented(); 
		 assertPojoMethodsForAll(testEntityClass)
			.testing(Method.GETTER, Method.SETTER, Method.TO_STRING, Method.EQUALS, Method.HASH_CODE)
			.areWellImplemented(); 
	}

}
