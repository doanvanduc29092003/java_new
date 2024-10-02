package com.microfvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microfvn.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String username);
}
