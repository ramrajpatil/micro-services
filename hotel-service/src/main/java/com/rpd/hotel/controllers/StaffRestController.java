package com.rpd.hotel.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
@CrossOrigin
public class StaffRestController {

	@GetMapping
	public ResponseEntity<List<String>> getStaff(){
		List<String> list = Arrays.asList("Ram", "Shyam","Sita", "Laxman", "Krushn");
		
		return ResponseEntity.ok(list);
	}
}
