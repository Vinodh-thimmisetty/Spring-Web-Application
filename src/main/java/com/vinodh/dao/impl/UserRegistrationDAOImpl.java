package com.vinodh.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vinodh.dao.UserRegistrationDAO;
import com.vinodh.entity.ApplicationUser;

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
	public List<String> loadCountryDetails() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createNamedQuery("countries.loadAll");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> loadStateDetails(String searchterm) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createNamedQuery("states.loadAll").setParameter("searchterm",
				"%" + searchterm + "%");
		return query.getResultList();
	}

	@Override
	public void saveUserDetails(ApplicationUser applicationUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(applicationUser);
	}

}
