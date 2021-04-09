package com.safetynet.alerts.consumer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository {
	private final DataHandler dataHandler;
	private List<Person> persons;
	
	public PersonRepository(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
		persons = dataHandler.getData().getPersons();
	}
	
	public List<Person> findAll(){
		return persons;
	}
	
	public List<Person> findByAddress(String address){
		List<Person> personsFilteredByAddress = persons.stream()
				.filter(p -> p.getAddress().equalsIgnoreCase(address))
				.collect(Collectors.toList());
		return personsFilteredByAddress;
	}

	// ------------------ Methods for add, update or delete a Person ----------------- //
	public void add(Person person) {
		persons.add(person);
		dataHandler.save();
	}

	public void update(Person person) {
		List<Person> list = persons.stream()
				.filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastname().equals(person.getLastname()))
				.collect(Collectors.toList());
		if (!list.isEmpty()) {
			Person oldPerson = list.get(0);
			oldPerson.setAddress(person.getAddress());
			oldPerson.setCity(person.getCity());
			oldPerson.setZip(person.getZip());
			oldPerson.setPhone(person.getPhone());
			oldPerson.setEmail(person.getEmail());
			dataHandler.save();
		}
	}
	
	public void delete(Person person) {
		List<Person> list = persons.stream()
				.filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastname().equals(person.getLastname()))
				.collect(Collectors.toList());
		if (!list.isEmpty()) {
			Person personToDelete = list.get(0);
			persons.remove(personToDelete);
			dataHandler.save();
		}
	}
	
	
	


}
