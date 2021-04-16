package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FireStationService;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonService;

import dto.FireStationDTO;

@RestController
public class AlertsController {
	private final FireStationService fireStationService;
	private final MedicalRecordService medicalRecordService;
	private final PersonService personService;

	public AlertsController(FireStationService fireStationService, MedicalRecordService medicalRecordService,
			PersonService personService) {
		super();
		this.fireStationService = fireStationService;
		this.medicalRecordService = medicalRecordService;
		this.personService = personService;
	}
	
	@GetMapping(value ="firestation/{stationNumber}")
	public FireStationDTO getPeopleCoveredByAFireStation(@PathVariable String stationNumber) {
		List<Person> people = fireStationService.getPeopleCoveredByAFireStation(Integer.parseInt(stationNumber));
		List<Person> adults = personService.getAdults(people);
		List<Person> children = personService.getChildren(people);
		FireStationDTO fireDto = new FireStationDTO(people, adults.size(), children.size());
		return fireDto;
	}
	
	

}
