package com.safetynet.alerts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.service.FireStationService;

@RestController
public class FireStationController {
	private final FireStationService fireStationService;

	public FireStationController(FireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}
	
	

}
