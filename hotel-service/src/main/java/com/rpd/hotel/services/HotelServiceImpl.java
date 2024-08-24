package com.rpd.hotel.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpd.hotel.entities.Hotel;
import com.rpd.hotel.exp.ResourceNotFoundException;
import com.rpd.hotel.repos.HotelRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HotelServiceImpl implements IHotelService {

	@Autowired
	private HotelRepository hRepo;

	@Override
	public Hotel saveHotel(Hotel hotel) {
		String id = UUID.randomUUID().toString();
		hotel.setHotelId(id);
		return this.hRepo.save(hotel);
	}

	@Override
	public Hotel getSingleHotel(String hotelId) {
		Hotel savedHotel = this.hRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + hotelId + " doest not exists."));
		return savedHotel;
	}

	@Override
	public List<Hotel> getAllHotels() {

		return this.hRepo.findAll();
	}

	@Override
	public String deleteHotel(String hotelId) {
		Hotel hotel = this.hRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel with id: " + hotelId + " doest not exists."));

		this.hRepo.delete(hotel);
		return "Hotel with id: " + hotelId + " deleted successfully!!!";
	}

}
