package com.safetynet.alerts.consumer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.exception.EntityNotFoundException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

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
		else throw new EntityNotFoundException(MedicalRecord.class, lastName);
	}
	
	public MedicalRecord findByIdentity(String firstName, String lastName) {
		Optional<MedicalRecord> mr = medicalRecords.stream()
				.filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
				.findFirst();
		if (mr.isPresent()) return mr.get();
		else {
			String identifier = firstName + lastName;
			throw new EntityNotFoundException(MedicalRecord.class, identifier);
		}
	}
	
	
	public void add(MedicalRecord medicalRecord) {
		medicalRecords.add(medicalRecord);
		dataHandler.save();
	}

	public void update(MedicalRecord medicalRecord) {
		Optional<MedicalRecord> medicalRecordToUpdate = medicalRecords.stream().filter(m -> m.getFirstName().equals(medicalRecord.getFirstName())
				&& m.getLastName()
				.equals(medicalRecord.getLastName()))
				.findAny();
		if (medicalRecordToUpdate.isPresent()) {
			MedicalRecord m = medicalRecordToUpdate.get();
			m.setBirthdate(medicalRecord.getBirthdate());
			m.setMedications(medicalRecord.getMedications());
			m.setAllergies(medicalRecord.getAllergies());
			dataHandler.save();
		}
		else {
			String identifier = medicalRecord.getFirstName() + medicalRecord.getLastName();
			throw new EntityNotFoundException(MedicalRecord.class, identifier);
		}

	}

	public void delete(MedicalRecord medicalRecord) {
		Optional<MedicalRecord> medicalRecordToDelete = medicalRecords.stream().filter(m -> m.getFirstName().equals(medicalRecord.getFirstName())
				&& m.getLastName()
				.equals(medicalRecord.getLastName()))
				.findAny();
		if (medicalRecordToDelete.isPresent()) {
			MedicalRecord m = medicalRecordToDelete.get();
			medicalRecords.remove(m);
			dataHandler.save();
		}
		else {
			String identifier = medicalRecord.getFirstName() + medicalRecord.getLastName();
			throw new EntityNotFoundException(MedicalRecord.class, identifier);
		}
	}

}
