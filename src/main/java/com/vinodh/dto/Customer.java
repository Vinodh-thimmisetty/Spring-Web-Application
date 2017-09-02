package com.vinodh.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

	@NotEmpty //make sure name is not empty
	String name;

	@Range(min = 1, max = 150) //age need between 1 and 150
	int age;
 
}