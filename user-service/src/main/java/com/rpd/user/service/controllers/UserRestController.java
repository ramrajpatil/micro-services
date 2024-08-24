package com.rpd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rpd.user.service.entities.User;
import com.rpd.user.service.payload.ApiResponse;
import com.rpd.user.service.services.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserRestController {

	@Autowired
	private IUserService uService;

	// create
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = this.uService.saveUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	// single user get
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User singleUser = this.uService.getSingleUser(userId);

		return ResponseEntity.status(HttpStatus.OK).body(singleUser);
	}

	// all users get

	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = this.uService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	
	// delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
		String msg = this.uService.deleteUser(userId);
		ApiResponse response = ApiResponse.builder().message(msg).success(true).build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	// update
}
