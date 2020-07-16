package com.mastercard.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.challenge.service.RouteService;

@RestController
public class RouteController {
	@Autowired
	RouteService service;

	/**
	 * Process connected request and check if origin and destination are connected.
	 * 
	 * in case of error, return response as "no"
	 * 
	 * @param origin
	 * @param destination
	 * @return String "yes" or "no"
	 */
	@GetMapping(value = "/connected", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> connected(@RequestParam(required = false) String origin,
			@RequestParam(required = false) String destination) {
		
		String response = service.checkConnected(origin, destination);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
