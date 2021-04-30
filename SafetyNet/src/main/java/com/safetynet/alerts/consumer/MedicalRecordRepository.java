package com.safetynet.alerts.consumer;

import java.util.List;
import java.util.Optional;
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
	
	public List<MedicalRecord> findByLastName(String lastName) {
		List<MedicalRecord> list = medicalRecords.stream()
				.filter(m -> m.getLastName().equals(lastName))
				.collect(Collectors.toList());
		if (!list.isEmpty()) return list;
		else throw new RuntimeException("No medical records for this lastName: " + lastName);
	}
	
	public Optional<MedicalRecord> findByIdentity(String firstName, String lastName) {
		Optional<MedicalRecord> mr = medicalRecords.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		if (mr.isPresent()) return mr;
		else throw new RuntimeException("No medical records for " + firstName + " " + lastName);
	}
	
	
	public void add(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
		dataHandler.save();
	}

	public void update(MedicalRecord medicalRecord) {
		medicalRecords.stream().filter(m -> m.getFirstName().equals(medicalRecord.getFirstName())
				&& m.getLastName()
				.equals(medicalRecord.getLastName()))
				.findAny().ifPresent(m -> {
					m.setBirthdate(medicalRecord.getBirthdate());
					m.setMedications(medicalRecord.getMedications());
					m.setAllergies(medicalRecord.getAllergies());
					dataHandler.save();
				});
	}

	public void delete(MedicalRecord medicalRecord) {
		medicalRecords.stream()
		.filter(m -> m.getFirstName().equals(medicalRecord.getFirstName())
						&& m.getLastName().equals(medicalRecord.getLastName()))
		.findAny().ifPresent(m -> {
			medicalRecords.remove(m);
			dataHandler.save();
		});
		
	}

}
