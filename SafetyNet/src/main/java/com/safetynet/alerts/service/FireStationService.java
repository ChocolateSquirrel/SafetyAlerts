package com.safetynet.alerts.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;

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

	// ------------------ Methods for add, update or delete a fireStation  ----------------- //
	public void addFireStation(FireStation fireStation) {
		fireStationRepository.saveFireStation(fireStation);
	}

	public void updateFireStation(FireStation fireStation) {
		fireStationRepository.updateFireStation(fireStation);
	}

	public void deleteFireStation(FireStation fireStation) {
		fireStationRepository.delete(fireStation);
	}

	public List<FireStation> getAllFireStations() {
		return fireStationRepository.findAll();
	}

	public List<FireStation> getFireStationsByNumber(int stationNumber) {
		return fireStationRepository.findByStationNumber(stationNumber);
	}

	public List<String> getAddressesCoveredByAFireStation(int stationNumber) {
		List<String> addressCoveredByStation = new ArrayList<>();
		for (FireStation fireStation : getFireStationsByNumber(stationNumber)) {
			addressCoveredByStation.add(fireStation.getAddress());
		}
		return addressCoveredByStation;
	}

	public List<Person> getPeopleCoveredByAFireStation(int stationNumber) {
		List<Person> personsCoverredByFireStation = new ArrayList<>();
		for (String address : getAddressesCoveredByAFireStation(stationNumber)) {
			personsCoverredByFireStation.addAll(personRepository.findByAddress(address));
		}
		return personsCoverredByFireStation;
	}

}
