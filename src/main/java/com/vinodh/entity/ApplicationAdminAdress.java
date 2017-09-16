package com.vinodh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "VINODH", name = "ADMIN_ADDRESS_INFORMATION")
public class ApplicationAdminAdress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private int addreessId;
	@Column(name = "DOOR_NUMBER")
	private String doorNumber;
	@Column(name = "STREET_NAME")
	private String street;
	@Column(name = "CITY_NAME")
	private String city;
	@Column(name = "DISTRICT_NAME")
	private String district;
	@Column(name = "STATE_NAME")
	private String state;
	@Column(name = "COUNTRY_NAME")
	private String country;
	@Column(name = "CONTINENT")
	private String continent;

}
