package com.rpd.hotel.services;

import java.util.List;

import com.rpd.hotel.entities.Hotel;

public interface IHotelService {

	// Create
	Hotel saveHotel(Hotel hotel);
	
	// get single
	Hotel getSingleHotel(String hotelId);
	
	// get all
	List<Hotel> getAllHotels();
	
	// delete hotel
	String deleteHotel(String hotelId);
}
