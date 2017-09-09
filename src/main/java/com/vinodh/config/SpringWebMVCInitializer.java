package com.vinodh.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * web.xml File
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
public class SpringWebMVCInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebMVCApplicationContext.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
