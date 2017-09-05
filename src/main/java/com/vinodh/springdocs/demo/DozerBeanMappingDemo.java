package com.vinodh.springdocs.demo;

import org.dozer.DozerBeanMapper;

import com.vinodh.dto.UserRegistrationForm;
import com.vinodh.entity.ApplicationUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DozerBeanMappingDemo {
	public static void main(String[] args) {
		UserRegistrationForm form = new UserRegistrationForm();
		form.setFirstName("Vinodh");

		ApplicationUser user = new DozerBeanMapper().map(form, ApplicationUser.class);

		log.info(">>>" + user.getFirstName());
	}
}
