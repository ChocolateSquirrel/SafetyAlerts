package com.safetynet.alerts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.service.FireStationService;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonService;

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

}
