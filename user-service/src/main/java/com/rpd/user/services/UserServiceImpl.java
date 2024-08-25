package com.rpd.user.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rpd.user.entities.Hotel;
import com.rpd.user.entities.Rating;
import com.rpd.user.entities.User;
import com.rpd.user.exp.ResourceNotFoundException;
import com.rpd.user.repos.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository uRepo;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User getSingleUser(String userId) {

		User user = this.uRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id: " + userId + " not found."));

		// fetch rating of the above user from rating service
		Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(),
				Rating[].class);
		// Because, above method was giving us LinkedHasMap and we needed List of Ratings.
		// So first we got Array of ratings and then that array converted to List.
		
//		logger.info(" "+userRatings);
		List<Rating> list = Arrays.stream(userRatings).map(rating -> {
			// api call to hotel service to get hotel
			// http://localhost:8082/hotels/691d17b2-8a1e-412f-8722-aaf18f79dcf1
			ResponseEntity<Hotel> response = restTemplate
					.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
			Hotel hotel = response.getBody();
			logger.info("Response status code: {} ",response.getStatusCode());

			// Set the hotel to rating
			rating.setHotel(hotel);

			// return the rating.
			return rating;

		}).collect(Collectors.toList());

		user.setRatings(list);

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
		return "User with id: " + userId + " deleted successfully!!!";
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
