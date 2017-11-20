package com.vinodh.repository;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

@Component
public class SQLQueriesList {

	public String listAllAuthUsers() {
		return new SQL().SELECT("USER_ID", "USER_FIRST_NAME", "USER_LAST_NAME", "USER_EMAIL", "USER_PHONE", "USER_ROLE",
				"APPLICATION_NAME").toString();
	}
}
