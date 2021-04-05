package com.safetynet.alerts.consumer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Person;

public class Data {
	List<Person> personsList;
	Map<Integer, FireStation> fireStationsMap;
	List<MedicalRecords> medicalRecordsList;

	public Data(String path) throws IOException {
		Path filePath = Paths.get(path);
		byte[] fileBytes = null;
		fileBytes = Files.readAllBytes(filePath);
		JsonIterator iter = JsonIterator.parse(fileBytes);
		Any any = iter.readAny();

		Any personAny = any.get("persons");
		personsList = new ArrayList<>();
		for (Any p : personAny) {
			Person person = new Person(p.get("firstName").toString(), p.get("lastName").toString(),
					p.get("address").toString(), p.get("city").toString(), p.get("zip").toString(),
					p.get("phone").toString(), p.get("email").toString());
			personsList.add(person);
		}

		Any fireStationAny = any.get("firestations");
		fireStationsMap = new HashMap<>();
		for (Any f : fireStationAny) {
			FireStation fireStation = new FireStation(Integer.parseInt(f.get("station").toString()));
			fireStation.addAddress(f.get("address").toString());
			int key = fireStation.getNumberStation();
			if (fireStationsMap.containsKey(key)) {
				FireStation fireStationSelected = fireStationsMap.get(key);
				fireStationSelected.addAddress(f.get("address").toString());
			}
			else {
				fireStationsMap.put(Integer.parseInt(f.get("station").toString()), fireStation);
			}
		}

		Any medicationsRecordsAny = any.get("medicalrecords");
		medicalRecordsList = new ArrayList<>();
		for (Any m : medicationsRecordsAny) {

			List<String> medicationsList = new ArrayList<>();
			for (Any medicationsAny : m.get("medications").asList()) {
				medicationsList.add(medicationsAny.toString());
			}
			List<String> allergiesList = new ArrayList<>();
			for (Any allergiesAny : m.get("allergies").asList()) {
				allergiesList.add(allergiesAny.toString());
			}

			MedicalRecords medicalrecords = new MedicalRecords(m.get("firstName").toString(),
					m.get("lastName").toString(), m.get("birthdate").toString(), medicationsList, allergiesList);
			medicalRecordsList.add(medicalrecords);
		}
	}

	/**
	 * @return the personsList
	 */
	public List<Person> getPersonsList() {
		return personsList;
	}

	/**
	 * @param personsList the personsList to set
	 */
	public void setPersonsList(List<Person> personsList) {
		this.personsList = personsList;
	}

	/**
	 * @return the fireStationsMap
	 */
	public Map<Integer, FireStation> getFireStationsMap() {
		return fireStationsMap;
	}

	/**
	 * @param fireStationsMap the fireStationsMap to set
	 */
	public void setFireStationsMap(Map<Integer, FireStation> fireStationsMap) {
		this.fireStationsMap = fireStationsMap;
	}

	/**
	 * @return the medicalRecordsList
	 */
	public List<MedicalRecords> getMedicalRecordsList() {
		return medicalRecordsList;
	}

	/**
	 * @param medicalRecordsList the medicalRecordsList to set
	 */
	public void setMedicalRecordsList(List<MedicalRecords> medicalRecordsList) {
		this.medicalRecordsList = medicalRecordsList;
	}

}
