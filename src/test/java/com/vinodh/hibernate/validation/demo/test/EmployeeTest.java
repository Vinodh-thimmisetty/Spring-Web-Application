package com.vinodh.hibernate.validation.demo.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.vinodh.hibernate.validation.demo.Employee;

public class EmployeeTest {

	Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void test1() {
		Employee e = new Employee();

		Set<ConstraintViolation<Employee>> violations = validator.validate(e);

		System.out.println(">>>>" + violations.size());

		e.setFirstName("e");
		Set<ConstraintViolation<Employee>> violations2 = validator.validate(e, Employee.Group1.class,
				Employee.Group2.class);

		System.out.println("<<<" + violations2.size());

		for (ConstraintViolation<Employee> constraintViolation : violations2) {
			System.out.println(constraintViolation.getMessage());
		}

		Set<ConstraintViolation<Employee>> violations3 = validator.validate(e, Employee.Group2.class);
		e.setFirstName("e");
		System.out.println("...." + violations3.size());
		for (ConstraintViolation<Employee> constraintViolation : violations3) {
			System.out.println(constraintViolation.getMessage());
		}
	}
}
