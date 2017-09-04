package com.vinodh.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.entity.Country;

//@formatter:off
/**
 * Load common Session Factory
 * Load Individual Session
 * Perform individual Transaction
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
// @formatter:on
@Repository
public class UserRegistrationDAOImpl implements UserRegistrationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> loadCountryDetails() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createNamedQuery("countries.loadAll");
		return query.getResultList();
	}

}
