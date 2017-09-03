package com.vinodh.repository;

import org.springframework.data.repository.CrudRepository;

import com.vinodh.entity.ApplicationUser;

public interface UserRepository extends CrudRepository<ApplicationUser, Long> {

}
