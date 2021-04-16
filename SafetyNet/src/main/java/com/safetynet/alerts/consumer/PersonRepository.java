package com.safetynet.alerts.consumer;

import java.util.List;
import java.util.Optional;
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
				.filter(p -> address.equalsIgnoreCase(p.getAddress()))
				.collect(Collectors.toList());
		return personsFilteredByAddress;
	}
	
	public List<Person> findByCity(String city){
		List<Person> personsFilteredByCity = persons.stream()
				.filter(p -> city.equalsIgnoreCase(p.getCity()))
				.collect(Collectors.toList());
		return personsFilteredByCity;
	}
	
	public List<Person> findByLastName(String lastName){
		List<Person> personFilteredByLastName = persons.stream()
				.filter(p -> lastName.equalsIgnoreCase(p.getLastName()))
				.collect(Collectors.toList());
		return personFilteredByLastName;
	}
	
	public Optional<Person> findByIdentity(String firstName, String lastName){
		Optional<Person> person = persons.stream()
				.filter(p -> firstName.equalsIgnoreCase(p.getFirstName()) && lastName.equals(p.getLastName()))
				.findFirst();
		return person;
	}

	// ------------------ Methods for add, update or delete a Person ----------------- //
	public void add(Person person) {
		persons.add(person);
		dataHandler.save();
	}

	public void update(Person person) {
		List<Person> list = persons.stream()
				.filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
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
				.filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
				.collect(Collectors.toList());
		if (!list.isEmpty()) {
			Person personToDelete = list.get(0);
			persons.remove(personToDelete);
			dataHandler.save();
		}
	}
	
	
	


}
