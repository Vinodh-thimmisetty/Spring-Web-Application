package com.vinodh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * To Demonstrate the Hibernate ONE-ONE Mapping
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(schema = "VINODH", name = "ADMIN_INFORMATION")
public class ApplicationAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADMIN_ID")
	private int adminId;
	@Column(name = "ADMIN_FIRST_NAME")
	private String firstName;
	@Column(name = "ADMIN_LAST_NAME")
	private String lastName;
	@Column(name = "ADMIN_LOGIN_NAME")
	private String adminLoginName;
	@Column(name = "ADMIN_PASSWORD")
	private String password;
	@Column(name = "ADMIN_EMAIL")
	private String email;
	@Column(name = "ADMIN_PHONE")
	private long phoneNumber;

	// Admin Personal Address
	@OneToOne
	private ApplicationAdminAdress homeAddress;

	// Admin Office Address
	@OneToOne
	private ApplicationAdminAdress officeAddress;

}
