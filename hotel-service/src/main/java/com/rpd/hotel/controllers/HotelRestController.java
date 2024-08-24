package com.rpd.hotel.controllers;

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

import com.rpd.hotel.entities.Hotel;
import com.rpd.hotel.payload.ApiResponse;
import com.rpd.hotel.services.IHotelService;

@RestController
@RequestMapping("/hotels")
@CrossOrigin
public class HotelRestController {

	@Autowired
	private IHotelService hService;

	// Create new hotel
	@PostMapping
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.hService.saveHotel(hotel));
	}
	
	// get single hotel
	@GetMapping("{hotelId}")
	public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId) {
		return ResponseEntity.ok(this.hService.getSingleHotel(hotelId));
	}
	
	// get all hotels
	@GetMapping
	public ResponseEntity<List<Hotel>> getSingleHotel() {
		return ResponseEntity.ok(this.hService.getAllHotels());
	}
	
	// delete hotel
	@DeleteMapping("{hotelId}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelId) {
		String msg = this.hService.deleteHotel(hotelId);
		ApiResponse response = ApiResponse.builder().message(msg).success(true).build();
		return ResponseEntity.ok(response);
	}
}
