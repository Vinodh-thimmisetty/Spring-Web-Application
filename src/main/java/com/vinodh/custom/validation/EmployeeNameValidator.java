package com.vinodh.custom.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.vinodh.util.custom.annotations.ValidEmpName;

@Component
public class EmployeeNameValidator implements ConstraintValidator<ValidEmpName, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			value = "Vinodh Kumar";
		}
		return false;
	}

}
