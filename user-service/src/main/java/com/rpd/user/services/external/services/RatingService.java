package com.rpd.user.services.external.services;

//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.rpd.user.entities.Rating;

//@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	// GET
	@GetMapping("/ratings/{ratingId}")
	Rating getSinglerating(@PathVariable String ratingId);
	
	// POST
	@PostMapping("/ratings")
	Rating createrating(Rating values);
	
	// DELETE
	@DeleteMapping("/ratings/{ratingId}")
	void deleteRating(@PathVariable String ratingId);
	
	// PUT	
	@PutMapping("/ratings/{ratingId}")
	Rating updateRating(@PathVariable String ratingId, Rating rating);
}
