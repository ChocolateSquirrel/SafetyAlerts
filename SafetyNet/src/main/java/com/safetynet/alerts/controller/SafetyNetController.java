package com.safetynet.alerts.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.consumer.SafetyNetRepository;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Person;

@RestController
public class SafetyNetController {
	
	@Autowired
	private SafetyNetRepository dataRepository;
	
	@GetMapping(value = "persons")
	public List<Person> afficherPersonnes(){
		return dataRepository.findAllPersonn();
	}
	
	@GetMapping(value = "medications")
	public List<MedicalRecords> afficherMedications(){
		return dataRepository.findAllMedicalRecords();
	}
	
	@GetMapping(value = "firestations")
	public Map<Integer, FireStation> afficherFireStations(){
		return dataRepository.findAllStation();
	}
	

}
