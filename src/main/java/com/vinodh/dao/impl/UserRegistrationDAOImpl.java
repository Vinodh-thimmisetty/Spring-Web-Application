package com.vinodh.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

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
@Transactional
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

	@Override
	public boolean isValidEmail(String userEmail) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createNamedQuery("email.exists").setParameter("userEmail", userEmail);
		return query.getResultList().isEmpty();

	}

	@Override
	public boolean isValidUserName(String userName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createNamedQuery("username.exists").setParameter("userName", userName);
		return query.getResultList().isEmpty();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationUser> loadAllUserDetails() {
		Session currentSession = sessionFactory.getCurrentSession();
		return (List<ApplicationUser>) currentSession.createNamedQuery("load.allUsers");
	}

}
