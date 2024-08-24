package com.rpd.rating.services;

import java.util.List;

import com.rpd.rating.entities.Rating;

public interface IRatingService {

	// create rating
	Rating saveRating(Rating rating);

	// get single rating
	Rating getSingleRating(String ratingId);

	// get all ratings
	List<Rating> getAllRatings();

	// Get all by user
	List<Rating> getAllRatingsByUserId(String userId);

	// Get all By hotel
	List<Rating> getAllRatingsByHotelId(String hotelId);

	// delete rating
	String deleteRating(String ratingId);

	// update rating
	Rating updateRating(Rating rating);
}
