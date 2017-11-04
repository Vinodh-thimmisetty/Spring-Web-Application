package com.vinodh.hibernate.validation.demo;

import lombok.Data;

@Data
public class Address {

	private String street;
	private String zip;
	private String city;
	private String state;
	private String country;
}
