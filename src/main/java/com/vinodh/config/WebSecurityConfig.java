package com.vinodh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		
		 http.authorizeRequests()  
	        .antMatchers("/", "/home").permitAll()
	        .antMatchers("/admin/**").access("hasRole('ADMIN')")
	        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	        .and().formLogin().loginPage("/login")
	        .usernameParameter("ssoId").passwordParameter("password")
	        .and().csrf()
	        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	/**
	 * 
	 * Individual Authentication Roles
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		detailsManager.createUser(User.withUsername("vinodh").password("vinodh").roles("USER").build());
		return detailsManager;

	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN", "DBA");
	}
}
