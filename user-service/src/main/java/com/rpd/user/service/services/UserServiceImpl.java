package com.rpd.user.service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpd.user.service.entities.User;
import com.rpd.user.service.exp.ResourceNotFoundException;
import com.rpd.user.service.repos.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository uRepo;

	@Override
	public User getSingleUser(String userId) {

		User user = this.uRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id: " + userId + " not found."));
		return user;
	}

	@Override
	public User saveUser(User user) {
		// Generate Unique id. (Realtime Project)
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return this.uRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {

		return this.uRepo.findAll();
	}

	@Override
	public String deleteUser(String userId) {
		User user = this.uRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id: " + userId + " not found."));
		this.uRepo.delete(user);
		return "User with id: "+userId+" deleted successfully!!!";
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
