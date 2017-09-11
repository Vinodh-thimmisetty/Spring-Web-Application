package com.vinodh.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * This will serve as web.xml File.
 * 
 * All web related Components must load into WebApplication context which will
 * be used by Dispatcher Servlet.
 * 
 * Other Components like Struts, Spring Security, Spring Data, any Database
 * related configurations will be loaded into Spring Application context which
 * will loaded using Contextconfiguration.
 * 
 * Single Dispatcher Servlet && Multiple Application Context Servlets
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
public class SpringWebMVCInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootApplicationContext.class };
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
