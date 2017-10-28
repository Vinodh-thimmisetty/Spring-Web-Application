package com.vinodh.repository;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.vinodh.domain.AuthenticationUser;

/**
 * 
 * 
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Repository
public interface AuthenticationUserDetailsDAO {
 
	 public static final String LIST_ALL_USERS = "SELECT USER_ID,"
								+ "USER_FIRST_NAME,"
								+ "USER_LAST_NAME,"
								+ "USER_EMAIL,"
								+ "USER_PHONE,"
								+ "USER_ROLE,"
								+ "APPLICATION_NAME,"
								+ "LAST_MODIFIED_BY,"
								+ "LAST_MODIFIED_DATE"
								+ " FROM VINODH.AUTHENTICATION_USER_DETAILS";

	@Select(LIST_ALL_USERS)
	@Results(value= {
		@Result(property="role",column="USER_ROLE",javaType=String.class)	 
	})
	List<AuthenticationUser> listAllAuthUsers();

	AuthenticationUser getAuthUser(String userId);

	int insertAuthUser(AuthenticationUser authenticationUser, String loggedInUser);

	int updateAuthUser(AuthenticationUser authenticationUser, String loggedInUser);

	int mergeAuthUser(AuthenticationUser authenticationUser, String loggedInUser);

	int deleteAuthUser(String userId, String loggedInUser);
}
