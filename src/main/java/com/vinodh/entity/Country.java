package com.vinodh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "VINODH", name = "COUNTRIES_LIST")
@Data
@NoArgsConstructor
@NamedQuery(name = "countries.loadAll", query = " from Country c")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COUNTRY_ID")
	private int countryId;
	@Column(name = "COUNTRY_NAME")
	private String countryName;
	@Column(name = "COUNTRY_CODE")
	private String countryCode;
}
