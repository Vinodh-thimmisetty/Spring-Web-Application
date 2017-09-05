package com.vinodh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.vinodh.custom.exceptions.EmailExistException;
import com.vinodh.custom.exceptions.UserNameExistException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(basePackages="com.vinodh.web")
@Slf4j
public class WebApplicationControllerAdvice {

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		// All dates are formatted
		final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		// All Form Data(always better to map form field to String) are Trimmed
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@ExceptionHandler({ EmailExistException.class, UserNameExistException.class })
	public void logError(HttpServletRequest request, Exception exception) {
		log.error("Exception::{}", exception.getMessage());
	}
}
