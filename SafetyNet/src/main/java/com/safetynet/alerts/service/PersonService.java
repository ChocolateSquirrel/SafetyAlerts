package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.consumer.FireStationRepository;
import com.safetynet.alerts.consumer.MedicalRecordRepository;
import com.safetynet.alerts.consumer.PersonRepository;
import com.safetynet.alerts.model.Person;

@Service
public class PersonService {

	private final PersonRepository personRepository;
	private final FireStationRepository fireStationRepository;
	private final MedicalRecordRepository medicalRecordRepository;

	public PersonService(PersonRepository personRepository, FireStationRepository fireStationRepository,
			MedicalRecordRepository medicalRecordRepository) {
		super();
		this.personRepository = personRepository;
		this.fireStationRepository = fireStationRepository;
		this.medicalRecordRepository = medicalRecordRepository;
	}

	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	// ------------------ Methods for add, update or delete a Person ----------------- //
	public void addPerson(Person person) {
		personRepository.add(person);
	}

	public void updatePerson(Person person) {
		personRepository.update(person);
	}
	
	public void deletePerson(Person person) {
		personRepository.delete(person);
	}
	
	public List<Person> getPersonInCity(String city){
		return personRepository.findByCity(city);
	}
	
	public List<String> getMail(List<Person> personList){
		List<String> emailList = new ArrayList<>();
		for (Person person : personList) {
			emailList.add(person.getEmail());
		}
		return emailList;
	}

}
