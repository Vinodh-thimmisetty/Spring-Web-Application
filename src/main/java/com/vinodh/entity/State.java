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
@Table(schema = "VINODH", name = "INDIAN_STATES")
@Data
@NoArgsConstructor
@NamedQuery(name = "states.loadAll",
			query = " select s.stateName from State s"
					+ " where UPPER(s.stateName) like UPPER(:searchterm)")
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STATE_ID")
	private int stateId;
	@Column(name = "STATE_NAME")
	private String stateName;
}
