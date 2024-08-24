package com.rpd.hotel.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpd.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
