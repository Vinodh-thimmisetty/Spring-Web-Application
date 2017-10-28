package com.vinodh.custom.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import com.vinodh.dto.CustomUserInfo;
import com.vinodh.util.custom.annotations.WithMockUserInformation;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUserInformation> {

	@Override
	public SecurityContext createSecurityContext(WithMockUserInformation annotation) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_APPLICATION_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_APPLICATION_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_OTHER"));
		CustomUserInfo customUserInfo = new CustomUserInfo("Mocked-Vinodh", "aaa", true, true, true, true, authorities);
		Authentication authentication = new UsernamePasswordAuthenticationToken(customUserInfo,
				customUserInfo.getPassword(), customUserInfo.getAuthorities());

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		return context;
	}

}
