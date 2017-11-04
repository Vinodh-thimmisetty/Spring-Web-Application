package com.vinodh.hibernate.validation.demo;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class Employee {

	public interface Group1 {
		// When Save is clicked. Validate all the constraints
	}

	public interface Group2 {
		// When Continue without fixing errors clicks, validate only notnull valid
		// fields
	}

	@NotBlank(groups = Group1.class)
	@Length(min = 2, max = 10, groups = { Group2.class })
	private String firstName;

	@NotBlank(groups = Group1.class)
	@Length(min = 2, max = 10, groups = { Group2.class })
	private String lastName;

	@NotBlank(groups = Group1.class)
	@Length(min = 2, max = 10, groups = { Group2.class })
	private String email;
}
