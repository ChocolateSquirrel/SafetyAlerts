package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FireStationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FireStationController {
	private final FireStationService fireStationService;

	public FireStationController(FireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}
	
	@GetMapping(value = "firestations")
	public List<FireStation> getAllFiresStations(){
		log.info("Request: GET /firestations");
		List<FireStation> fireStations = fireStationService.getAllFireStations();
		log.info("Response: " + JsonStream.serialize(fireStations));
		return fireStations;
	}
	
	@PostMapping(value = "firestation")
	public FireStation addFireStation(@RequestBody FireStation fs) {
		log.info("Request: POST /firestation");
		FireStation fireStation = new FireStation(fs.getAddress(), fs.getStation());
		fireStationService.addFireStation(fireStation);
		log.info("Response: " + JsonStream.serialize(fireStation));
		return fireStation;
	}
	
	@PutMapping(value = "firestation")
	public FireStation updateFireStation(@RequestBody FireStation fs) {
		log.info("Request: PUT /firestation");
		FireStation fireStation = new FireStation(fs.getAddress(), fs.getStation());
		fireStationService.updateFireStation(fireStation);
		log.info("Response: " + JsonStream.serialize(fireStation));
		return fireStation;
	}
	
	@DeleteMapping(value = "firestation")
	public FireStation deleteFireStation(@RequestBody FireStation fs) {
		log.info("Request: DELETE /firestation");
		FireStation fireStation = new FireStation(fs.getAddress(), fs.getStation());
		fireStationService.deleteFireStation(fireStation);
		log.info("Response: " + JsonStream.serialize(fireStation));
		return fireStation;
	}
	

}
