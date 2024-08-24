package com.rpd.user.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpd.user.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	// TO search users by the name
	List<User> findByNameContaining(String uName);
	
}
