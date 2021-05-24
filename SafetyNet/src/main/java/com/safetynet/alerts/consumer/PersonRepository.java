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

	public List<Person> findAll() {
		return persons;
	}

	public List<Person> findByAddress(String address) {
		List<Person> personsFilteredByAddress = persons.stream().filter(p -> address.equalsIgnoreCase(p.getAddress()))
				.collect(Collectors.toList());
		if (!personsFilteredByAddress.isEmpty()) return personsFilteredByAddress;
		else throw new RuntimeException("Anybody live at this address: " + address);
	}

	public List<Person> findByCity(String city) {
		List<Person> personsFilteredByCity = persons.stream().filter(p -> city.equalsIgnoreCase(p.getCity()))
				.collect(Collectors.toList());
		if (!personsFilteredByCity.isEmpty()) return personsFilteredByCity;
		else throw new RuntimeException("Anybody live in this city: " + city);
	}

	public List<Person> findByLastName(String lastName) {
		List<Person> personFilteredByLastName = persons.stream().filter(p -> lastName.equalsIgnoreCase(p.getLastName()))
				.collect(Collectors.toList());
		if (!personFilteredByLastName.isEmpty()) return personFilteredByLastName;
		else throw new RuntimeException("No one have this lastName: " + lastName);
	}

	public Person findByIdentity(String firstName, String lastName) {
		Optional<Person> person = persons.stream()
				.filter(p -> firstName.equalsIgnoreCase(p.getFirstName()) && lastName.equals(p.getLastName()))
				.findFirst();
		if (person.isPresent()) return person.get();
		else throw new RuntimeException("No one is called: " + firstName + " " + lastName);
	}

	// ----------------- Methods for add, update or delete a Person ----------------- //
	public void add(Person person) {
		persons.add(person);
		dataHandler.save();
	}

	public void update(Person person) {
		Optional<Person> personToUpdate = persons.stream().filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())).findAny();
		if (personToUpdate.isPresent()) {
			Person p = personToUpdate.get();
			p.setAddress(person.getAddress());
			p.setCity(person.getCity());
			p.setZip(person.getZip());
			p.setPhone(person.getPhone());
			p.setEmail(person.getEmail());
			dataHandler.save();
		}
		else throw new RuntimeException("No one is called: " + person.getFirstName() + " " + person.getLastName());
	}

	public void delete(Person person) {
		Optional<Person> personToDelete = persons.stream().filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())).findAny();
		if (personToDelete.isPresent()) {
			Person p = personToDelete.get();
			persons.remove(p);
			dataHandler.save();
		}
		else throw new RuntimeException("No one is called: " + person.getFirstName() + " " + person.getLastName());
	}

}
