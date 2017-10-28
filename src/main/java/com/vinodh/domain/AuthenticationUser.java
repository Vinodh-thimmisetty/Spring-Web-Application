package com.vinodh.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Holds Application specific Authentication and Authorization details.
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationUser implements UserDetails {
	private static final long serialVersionUID = -5623636169002512248L;
	private String userId;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userPhone;
	private String applicationName;
	private Role role;

	/**
	 * 
	 * SUPER_ADMIN is allowed to VIEW/ADD/EDIT/DELETE any content in any
	 * Application.
	 * 
	 * APPLICATION_ADMIN is allowed to VIEW/ADD/EDIT/DELETE any content in specific
	 * Application.
	 * 
	 * APPLICATION_USER is allowed only to VIEW any content in specific Application
	 * but cannot ADD/EDIT/DELETE the content.
	 * 
	 * OTHER is Role of end user who fill the forms in external application who can
	 * VIEW/ADD/EDIT/DELETE any content related to him/her for a application.
	 * 
	 * @author Vinodh Kumar Thimmisetty
	 *
	 */
	public enum Role {
		SUPER_ADMIN(2), APPLICATION_ADMIN(1), APPLICATION_USER(0), OTHER(-1);

		private final int value;

		private Role(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * 
	 * If none of the Roles exists in the Database matches to Role, Default Other
	 * Role is assigned
	 * 
	 * @param userRole
	 */
	public void setRole(String userRole) {
		if (EnumUtils.isValidEnum(Role.class, userRole)) {
			this.role = Role.valueOf(userRole);
		} else {
			this.role = Role.OTHER;
		}

	}

	/***
	 * 
	 * This authorities used for Spring Security context to identify the Role of an
	 * user who is accessing the Application.
	 * 
	 * 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		switch (this.role) {
		case SUPER_ADMIN:
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
			break;
		case APPLICATION_ADMIN:
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_APP_ADMIN"));
			break;
		case APPLICATION_USER:
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_APP_USER"));
			break;
		case OTHER:
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_OTHER"));
			break;
		default:
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_OTHER"));
			break;
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return "NA";
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
