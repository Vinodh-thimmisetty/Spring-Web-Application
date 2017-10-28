package com.vinodh.config;

import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 * Most useful Mybatis Annotations:
 * 
 * CRUD Operations:
 * 
 	 * @Insert("INSERT INTO TABLE_NAME(COL_1) VALUES(#{value_1})")
	 * @Select("SELECT COL_1 FROM TABLE WHERE COL_2 = #{primaryKey}")
	 * @Update("UPDATE TABLE SET COL_1 = #{value1} where COL_2=#{primaryKey}")
	 * @Delete("DELETE FROM TABLE where COL_1=#{primaryKey}")
	 
 *	Note: Merge is also used as part of Update annotation
 * 		@Update("MERGE TABLE_1, TABLE_2 ")
 * 
 *  TABLE-CLASS Mapping:
  	 * @Select("SELECT COL_1 FROM TABLE WHERE COL_2 = #{primaryKey}")
	   @Results(value={
	   		@Result(property="class-variable-name", column="table-col-name" ,javaType="List.class")
	   })
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@ContextConfiguration(classes = DatabaseConfig.class)
@MapperScan(basePackages = "com.vinodh.repository")
public class MyBatisConfig {

	@Autowired
	DatabaseConfig databaseConfig;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(databaseConfig.dataSource());
		sessionFactoryBean.setTypeAliasesPackage("com.vinodh.domain");
		SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
		sqlSessionFactory.getConfiguration().setAutoMappingBehavior(AutoMappingBehavior.FULL);
		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		return sessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(databaseConfig.dataSource());
	}

}
