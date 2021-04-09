package com.safetynet.alerts.service;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;

@Service
public class MedicalRecordService {
	private final PersonRepository personRepository;
	private final FireStationRepository fireStationRepository;
	private final MedicalRecordRepository medicalRecordRepository;

	public MedicalRecordService(PersonRepository personRepository, FireStationRepository fireStationRepository,
			MedicalRecordRepository medicalRecordRepository) {
		super();
		this.personRepository = personRepository;
		this.fireStationRepository = fireStationRepository;
		this.medicalRecordRepository = medicalRecordRepository;
	}

}
