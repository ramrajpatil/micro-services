package com.rpd.user.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.rpd.user.entities.User;
import com.rpd.user.payload.ApiResponse;
import com.rpd.user.services.IUserService;
import com.rpd.user.services.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserRestController {

	@Autowired
	private IUserService uService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	// create
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = this.uService.saveUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	int retryCount=1;
	// single user get
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelfallback")
//	@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User singleUser = this.uService.getSingleUser(userId);

		logger.info("retry count: ",retryCount);
		retryCount++;
		
		return ResponseEntity.status(HttpStatus.OK).body(singleUser);
	}

	// fallback method
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//		logger.info("Fallback is executed because service is down.", ex.getMessage());

		User user = User.builder()
				.email("dummy@gmail.com")
				.name("Dummy")
				.about("This user is created dummy because some service is down.")
				.userId("1234").build();
		return new ResponseEntity<>(user, HttpStatus.OK);
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
