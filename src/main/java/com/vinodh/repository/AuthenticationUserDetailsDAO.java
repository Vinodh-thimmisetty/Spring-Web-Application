package com.vinodh.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
 
	 final String LIST_ALL_USERS = "SELECT USER_ID,"
											+ "USER_FIRST_NAME,"
											+ "USER_LAST_NAME,"
											+ "USER_EMAIL,"
											+ "USER_PHONE,"
											+ "USER_ROLE,"
											+ "APPLICATION_NAME,"
											+ "LAST_MODIFIED_BY,"
											+ "LAST_MODIFIED_DATE"
											+ " FROM VINODH.AUTHENTICATION_USER_DETAILS";
							 
	 final String GET_USER = "SELECT * FROM VINODH.AUTHENTICATION_USER_DETAILS WHERE USER_ID = #{userId}";
	 
	 final String INSERT_USER ="INSERT INTO	VINODH.AUTHENTICATION_USER_DETAILS "
	 						+ "(USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,USER_PHONE,"
	 						+ "USER_ROLE,APPLICATION_NAME,LAST_MODIFIED_BY,LAST_MODIFIED_DATE)"
	 						+ " VALUES(#{authenticationUser.userId},#{authenticationUser.userFirstName},#{authenticationUser.userLastName},"
	 						+ "#{authenticationUser.userEmail},#{authenticationUser.userPhone},"
	 						+ "#{authenticationUser.role},#{authenticationUser.applicationName},#{loggedInUser},SYSDATE)";
	 
	 final String UPDATE_USER = "UPDATE VINODH.AUTHENTICATION_USER_DETAILS"
			 					+ " SET USER_ROLE = #{authenticationUser.role},"
			 					+ " LAST_MODIFIED_BY = 	#{loggedInUser}"
			 					+ " WHERE USER_ID = #{authenticationUser.userId}";
	 
	 final String MERGE_USER = "MERGE INTO VINODH.AUTHENTICATION_USER_DETAILS SOURCE"
			 				+ " USING"
			 				+ " (SELECT 1 FROM DUAL) TARGET" /* Merge operation on Same Table, so using the DUAL*/
			 				+ "	ON (SOURCE.USER_ID = #{authenticationUser.userId})"
			 				+ " WHEN MATCHED THEN " /* Update if already exists*/
			 				+ " UPDATE SET SOURCE.USER_ROLE = #{authenticationUser.role}, SOURCE.LAST_MODIFIED_BY = #{loggedInUser}"
		 					+ " WHEN NOT MATCHED THEN " /* Insert a New Record if User doesn't exists already*/
		 					+ " INSERT (USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_EMAIL,USER_PHONE,USER_ROLE,APPLICATION_NAME,LAST_MODIFIED_BY,LAST_MODIFIED_DATE)"
		 					+ " VALUES(#{authenticationUser.userId},#{authenticationUser.userFirstName},#{authenticationUser.userLastName},#{authenticationUser.userEmail},#{authenticationUser.userPhone},#{authenticationUser.role},#{authenticationUser.applicationName},#{loggedInUser},SYSDATE)";
	 
	 final String DELETE_USER = "DELETE FROM VINODH.AUTHENTICATION_USER_DETAILS"
	 							+ " WHERE USER_ID = #{userId}";
	 
	@Select(LIST_ALL_USERS)
	@Results(value = { @Result(property = "role", column = "USER_ROLE", javaType = String.class) })
	
	List<AuthenticationUser> listAllAuthUsers();

	@Select(GET_USER)
	@Results(value = { @Result(property = "role", column = "USER_ROLE", javaType = String.class) })
	AuthenticationUser getAuthUser(String userId);

	@Insert(INSERT_USER)
	@Options(keyColumn = "USER_ID", keyProperty = "userId")
	int insertAuthUser(@Param("authenticationUser") AuthenticationUser authenticationUser,
			@Param("loggedInUser") String loggedInUser);

	@Update(UPDATE_USER)
	int updateAuthUser(@Param("authenticationUser") AuthenticationUser authenticationUser,
			@Param("loggedInUser") String loggedInUser);

	@Update(MERGE_USER)
	int mergeAuthUser(@Param("authenticationUser") AuthenticationUser authenticationUser,
			@Param("loggedInUser") String loggedInUser);

	@Delete(DELETE_USER)
	int deleteAuthUser(@Param("userId") String userId, @Param("loggedInUser") String loggedInUser);
}
