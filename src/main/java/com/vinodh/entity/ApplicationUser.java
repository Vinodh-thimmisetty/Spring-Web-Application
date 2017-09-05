package com.vinodh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "VINODH", name = "USER_INFORMATION")
public class ApplicationUser implements Serializable {

	private static final long serialVersionUID = 1706865056988491882L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Transient
	private String userPasswordConfirm;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "USER_COUNTRY")
	private String country;
	@Column(name = "USER_STATE")
	private String state;
	@Column(name = "USER_PHONE")
	private String phone;
	@Column(name = "USER_GENDER")
	private String gender;
}
