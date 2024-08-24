package com.rpd.rating.controllers;

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

import com.rpd.rating.entities.Rating;
import com.rpd.rating.payload.ApiResponse;
import com.rpd.rating.services.IRatingService;

@RestController
@RequestMapping("/ratings")
@CrossOrigin
public class RatingRestController {

	@Autowired
	private IRatingService rService;
	
	
	@PostMapping
	public ResponseEntity<Rating> saveRating(@RequestBody Rating rating){
		Rating saveRating = this.rService.saveRating(rating);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveRating);
	}
	
	// get single rating
	@GetMapping("/{ratingId}")
	public ResponseEntity<Rating> getSingleRating(@PathVariable String ratingId){
		Rating rating = this.rService.getSingleRating(ratingId);
		
		return ResponseEntity.ok(rating);
	}
	
	// get all ratings
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings(){
		List<Rating> ratings = this.rService.getAllRatings();
		return ResponseEntity.ok(ratings);
	}
	
	// get all ratings by user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable String userId){
		List<Rating> ratings = this.rService.getAllRatingsByUserId(userId);
		return ResponseEntity.ok(ratings);
	}
	
	// get all rating by hotel
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotel(@PathVariable String hotelId){
		List<Rating> ratings = this.rService.getAllRatingsByHotelId(hotelId);
		
		return ResponseEntity.ok(ratings);
	}
	
	// delete ratings
	@DeleteMapping("{ratingId}")
	public ResponseEntity<ApiResponse> deleteRating(@PathVariable String ratingId){
		
		ApiResponse response = ApiResponse.builder().message(this.rService.deleteRating(ratingId)).success(true).build();
		
		return ResponseEntity.ok(response);
	}
}
