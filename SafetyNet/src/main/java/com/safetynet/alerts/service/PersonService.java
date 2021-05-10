package com.safetynet.alerts.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
public class PersonService {
	
	private final PersonRepository personRepository;
	private final FireStationRepository fireStationRepository;
	private final MedicalRecordRepository medicalRecordRepository;

	public PersonService(PersonRepository personRepository, FireStationRepository fireStationRepository,
			MedicalRecordRepository medicalRecordRepository) {
		this.personRepository = personRepository;
		this.fireStationRepository = fireStationRepository;
		this.medicalRecordRepository = medicalRecordRepository;
	}

	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
	public List<Person> getPersonByAddress(String address){
		return personRepository.findByAddress(address);
	}
	
	public List<Person> getPersonByLastName(String lastName){
		return personRepository.findByLastName(lastName);
	}
	
	public Person getPersonByIdentity(String firstName, String lastName) {
		return personRepository.findByIdentity(firstName, lastName);
	}

	// ------------------ Methods for add, update or delete a Person ----------------- //
	public void addPerson(Person person) {
		personRepository.add(person);
	}

	public boolean updatePerson(Person person) {
		return personRepository.update(person);
	}
	
	public boolean deletePerson(Person person) {
		return personRepository.delete(person);
	}
	
	
	

}
