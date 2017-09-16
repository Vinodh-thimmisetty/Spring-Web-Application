package com.vinodh.openpojo.test;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsForAll;

import org.junit.Test;

import com.vinodh.dto.ApplicationAdminDTO;
import com.vinodh.dto.CourseDTO;
import com.vinodh.dto.EmployeeDTO;
import com.vinodh.dto.InstructorDTO;
import com.vinodh.dto.Mail;
import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.entity.ApplicationUser;
import com.vinodh.entity.Country;
import com.vinodh.entity.State;

import pl.pojo.tester.api.PackageFilter;
import pl.pojo.tester.api.assertion.Method;

public class PojoTester_Test {
	public static final String PACKAGE_NAME = "com.vinodh.dto";
	PackageFilter packageFilter = new PackageFilter() {
		@Override
		public Class<?>[] getClasses() {
			return new Class[] { UserRegistrationForm.class, CourseDTO.class, EmployeeDTO.class, InstructorDTO.class,
					Mail.class, ApplicationAdminDTO.class };
		}
	};

	@Test
	public void pojoTester() {
		final Class<?>[] testDTOClass = { UserRegistrationForm.class, CourseDTO.class, EmployeeDTO.class,
				InstructorDTO.class, Mail.class, ApplicationAdminDTO.class };

		final Class<?>[] testEntityClass = { ApplicationUser.class, Country.class, State.class,
				com.vinodh.entity.Employee.class };

		// assertPojoMethodsForAll(testDTOClass);
		// assertPojoMethodsForAll(testEntityClass);
		// assertPojoMethodsForAll(packageFilter);
		assertPojoMethodsForAll(testDTOClass)
				.testing(Method.GETTER, Method.SETTER, Method.TO_STRING, Method.EQUALS, Method.HASH_CODE)
				.areWellImplemented();
		assertPojoMethodsForAll(testEntityClass)
				.testing(Method.GETTER, Method.SETTER, Method.TO_STRING, Method.EQUALS, Method.HASH_CODE)
				.areWellImplemented();
	}

	@Test
	public void missedCoverageTest() {
		assertPojoMethodsForAll(CourseDTO.class, EmployeeDTO.class, InstructorDTO.class, Mail.class,
				ApplicationAdminDTO.class, UserRegistrationForm.class).areWellImplemented();

	}

}
