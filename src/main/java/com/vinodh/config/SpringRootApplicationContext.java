package com.vinodh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Spring Root Application Context
 * 
 * 1. Loads Database related classes. 2. Autowire all Service ,DAO related
 * stuff.
 * 
 * In a nut shell, it is responsible to load any components other than WEB
 * components which will be loaded by Spring WebApplcation context.
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.vinodh" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = { "com.vinodh.web.*", "com.vinodh.config.test.*" }) })
@Import(value = { DatabaseConfig.class, EmailConfig.class })
public class SpringRootApplicationContext {
}
