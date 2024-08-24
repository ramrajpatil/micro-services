package com.rpd.user.service.services;

import java.util.List;

import com.rpd.user.service.entities.User;

public interface IUserService {
	
	User getSingleUser(String userId);
	
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	String deleteUser(String userId);
	
	User updateUser(User user);
}
