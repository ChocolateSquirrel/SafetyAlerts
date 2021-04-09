package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.FireStation;

@Service
public class FireStationService {
	private final PersonRepository personRepository;
	private final FireStationRepository fireStationRepository;
	private final MedicalRecordRepository medicalRecordRepository;

	public FireStationService(PersonRepository personRepository, FireStationRepository fireStationRepository,
			MedicalRecordRepository medicalRecordRepository) {
		super();
		this.personRepository = personRepository;
		this.fireStationRepository = fireStationRepository;
		this.medicalRecordRepository = medicalRecordRepository;
	}
	
	public List<FireStation> getAllFireStations(){
		return fireStationRepository.findAll();
	}

}
