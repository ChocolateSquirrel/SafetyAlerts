package com.safetynet.alerts.consumer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {
	private final DataHandler dataHandler;
	private List<MedicalRecord> medicalRecords;

	public MedicalRecordRepository(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
		medicalRecords = dataHandler.getData().getMedicalrecords();
	}

	public List<MedicalRecord> findAll() {
		return medicalRecords;
	}

	public void add(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
		dataHandler.save();
	}

	public void update(MedicalRecord medicalRecord) {
		List<MedicalRecord> list = medicalRecords.stream()
				.filter(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()) )
				.collect(Collectors.toList());
		if (!list.isEmpty()) {
			MedicalRecord oldMedicalRecord = list.get(0);
			oldMedicalRecord.setBirthdate(medicalRecord.getBirthdate());
			oldMedicalRecord.setMedications(medicalRecord.getMedications());
			oldMedicalRecord.setAllergies(medicalRecord.getAllergies());
			dataHandler.save();
		}		
	}
	
	public void delete(MedicalRecord medicalRecord) {
		List<MedicalRecord> list = medicalRecords.stream()
				.filter(m -> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()))
				.collect(Collectors.toList());
		if (!list.isEmpty()) {
			MedicalRecord medicalRecordToDelete = list.get(0);
			medicalRecords.remove(medicalRecordToDelete);
			dataHandler.save();
		}
	}

}
