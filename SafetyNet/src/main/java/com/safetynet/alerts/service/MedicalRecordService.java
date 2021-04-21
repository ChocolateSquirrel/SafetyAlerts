package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

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
	
	public List<MedicalRecord> getAllMedicalRecords(){
		return medicalRecordRepository.findAll();
	}

	public void addMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.add(medicalRecord);	
	}

	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.update(medicalRecord);
	}
	
	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.delete(medicalRecord);
	}
	
}
