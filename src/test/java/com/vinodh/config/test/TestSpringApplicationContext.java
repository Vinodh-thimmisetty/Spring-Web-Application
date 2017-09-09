package com.vinodh.config.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.vinodh.config.EmailConfig;

@Configuration
@ComponentScan(basePackages = { "com.vinodh" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.vinodh.web.*"),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@Import(value = { TestDatabaseConfig.class,EmailConfig.class })
public class TestSpringApplicationContext {

}
