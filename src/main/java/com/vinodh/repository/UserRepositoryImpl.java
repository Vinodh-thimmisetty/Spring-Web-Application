package com.vinodh.repository;

import org.springframework.stereotype.Repository;

import com.vinodh.entity.ApplicationUser;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public <S extends ApplicationUser> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends ApplicationUser> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationUser findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<ApplicationUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ApplicationUser> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ApplicationUser entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends ApplicationUser> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
