package com.rpd.rating.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rpd.rating.entities.Rating;
import com.rpd.rating.exp.ResourceNotFoundException;
import com.rpd.rating.repos.RatingRepository;

@Service
@Transactional
public class RatingServiceImpl implements IRatingService {

	@Autowired
	private RatingRepository rRepo;

	@Override
	public Rating saveRating(Rating rating) {

		return this.rRepo.save(rating);
	}

	@Override
	public Rating getSingleRating(String ratingId) {

		Rating rating = this.rRepo.findById(ratingId)
				.orElseThrow(() -> new ResourceNotFoundException("Rating not found with given id: " + ratingId));

		return rating;
	}

	@Override
	public List<Rating> getAllRatings() {

		return this.rRepo.findAll();
	}

	@Override
	public List<Rating> getAllRatingsByUserId(String userId) {

		return this.rRepo.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllRatingsByHotelId(String hotelId) {

		return this.rRepo.findByHotelId(hotelId);
	}

	@Override
	public String deleteRating(String ratingId) {
		Rating rating = this.rRepo.findById(ratingId)
				.orElseThrow(() -> new ResourceNotFoundException("Rating not found with given id: " + ratingId));
		this.rRepo.delete(rating);
		return "rating with id: "+ratingId+"  deleted succesfully!!!";
	}

	@Override
	public Rating updateRating(Rating rating) {

		return null;
	}

}
