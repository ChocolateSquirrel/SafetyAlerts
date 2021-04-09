package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FireStationService;

@RestController
public class FireStationController {
	private final FireStationService fireStationService;

	public FireStationController(FireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}
	
	@GetMapping(value = "firestations")
	public List<FireStation> getAllFiresStations(){
		return fireStationService.getAllFireStations();
	}
	
	@PostMapping(value = "firestation")
	public FireStation addFireStation(@RequestBody FireStation fs) {
		FireStation fireStation = new FireStation(fs.getAddress(), fs.getStation());
		fireStationService.addFireStation(fireStation);
		return fireStation;
	}
	
	@PutMapping(value = "firestation")
	public FireStation updateFireStation(@RequestBody FireStation fs) {
		FireStation fireStation = new FireStation(fs.getAddress(), fs.getStation());
		fireStationService.updateFireStation(fireStation);
		return fireStation;
	}
	
	@DeleteMapping(value = "firestation")
	public FireStation deleteFireStation(@RequestBody FireStation fs) {
		FireStation fireStation = new FireStation(fs.getAddress(), fs.getStation());
		fireStationService.deleteFireStation(fireStation);
		return fireStation;
	}
	
	
	@GetMapping(value = "firestation/{stationNumber}" )
	public List<Person> getPersonCoveredByAFireStation(@PathVariable int stationNumber){
		return fireStationService.getPersonsCoveredByAFireStation(stationNumber);
	}


}
