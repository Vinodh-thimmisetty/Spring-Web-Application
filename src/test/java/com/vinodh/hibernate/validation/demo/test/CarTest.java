package com.vinodh.hibernate.validation.demo.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vinodh.hibernate.validation.demo.Car;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarTest {

	private static Validator validator;

	@BeforeClass
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void mfgNameNull() {
		Car c = Car.builder().manufacturer("Vinodh").seatCount(2).licensePlate("12345").isGood(true).build();

		Set<ConstraintViolation<Car>> constraintViolations = validator.validate(c);

		Assert.assertEquals(2, constraintViolations.size());
		//Assert.assertEquals("must not be null", constraintViolations.iterator().next().getMessage());

		c.setMfgCountry("India");
		constraintViolations = validator.validate(c);
		Assert.assertEquals(1, constraintViolations.size());

		List<String> descripton = Arrays.asList("", "1", "Vinodh", "Thimmisetty Vinodh");
		c.setDescription(descripton);
		constraintViolations = validator.validate(c);
		// Don't allow any String having Null, length<2 && length >10 in the List
		Assert.assertEquals(4, constraintViolations.size());

		Set<String> emails = descripton.stream().collect(Collectors.toSet());
		c.setEmails(emails);
		constraintViolations = validator.validate(c);
		// Don't allow any String having Null, length<2 && length >10 in the Set
		Assert.assertEquals(7, constraintViolations.size());

		Map<String, String> marks = new HashMap<>();
		marks.put("Telugu", "90");
		marks.put("Hindi", "90");
		marks.put("English", "90");
		marks.put("Maths", "90");
		marks.put("Science", "90");
		marks.put("Social", "90");
		marks.put("a", "1234567890");

		c.setMarks(marks);
		constraintViolations = validator.validate(c);
		Assert.assertEquals(9, constraintViolations.size());

		// The Validator interface contains three methods that can be used to either
		// validate entire entities or just single properties of the entity.
		c.setManufacturer(null);
		constraintViolations = validator.validateProperty(c, "manufacturer");
		constraintViolations = validator.validateValue(Car.class, "manufacturer", "null");

		// ConstraintViolation Methods
		//log.info("Error Message:: {}", constraintViolations.iterator().next().getPropertyPath());
		Iterator<ConstraintViolation<Car>> iterator = constraintViolations.iterator();
		while (iterator.hasNext()) {
			ConstraintViolation<Car> constraintViolation = constraintViolations.iterator().next();
			//log.info("Error Message:: {}", constraintViolation.getMessageTemplate());

		}
	}

}
