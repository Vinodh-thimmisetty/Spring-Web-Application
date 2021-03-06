package com.vinodh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = "ADMIN";

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/webjars/**");
	}

	//@formatter:off 
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		
		// Ordering has to be from most specific to Least Specific URL Pattern
		 http
		 	.authorizeRequests()  
		 		.antMatchers("/", "/home","/test/**").permitAll() 
		 		.antMatchers("/admin/authHomePage**").access("hasRole('SUPER_ADMIN')")
		 		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		 		.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
		 		.antMatchers("/customUser/**","/customUser_CurrentUser/**","/customUser_Secure/**").access("hasRole('USER')")
		 		.antMatchers("/user/signup").permitAll()
		 		.antMatchers("/admin/loadAdminHomePage").access("hasRole('ADMIN')")
		 		.antMatchers("/course/list").access("hasRole('USER')")
	        .and()
	        	.formLogin().loginPage("/login").permitAll() 
	        .and()
	        	.logout().logoutUrl("/logout")
	        .and()
	        	.exceptionHandling().accessDeniedPage("/Access_Denied") 
		   .and()	// Enabled By Default of <form:form> is used. It will Block the PUT and DELETE actions
		         .csrf().disable(); 
	}
	//@formatter:on
	/**
	 * 
	 * Individual Authentication Roles
	 */
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		detailsManager.createUser(User.withUsername("vinodh").password("vinodh").roles("USER").build());
		return detailsManager;

	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		// Memory Database
		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("root123").roles(ADMIN);
		auth.inMemoryAuthentication().withUser("dba").password("root123").roles(ADMIN, "DBA");
		auth.inMemoryAuthentication().withUser("Vinodh").password("abcd1234").roles("SUPER_ADMIN", "APPLICATION_ADMIN",
				"APPLICATION_USER", ADMIN, "USER", "DBA");

		// Custom User Database
	}

}
