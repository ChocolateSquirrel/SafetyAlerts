package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonController {
	
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping(value = "persons")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping(value = "communityEmail/{city}")
	public List<String> getEmailOfPersonInCity(@PathVariable String city){
		return personService.getMail(personService.getPersonInCity(city));
	}
	
	@PostMapping(value = "person")
	public Person addPerson(@RequestBody Person ps) {
		Person person = new Person(ps.getFirstName(),
				ps.getLastName(),
				ps.getAddress(),
				ps.getCity(),
				ps.getZip(),
				ps.getPhone(),
				ps.getEmail());
		personService.addPerson(person);
		return person;
	}
	
	@PutMapping(value = "person")
	public Person updatePerson(@RequestBody Person ps) {
		Person person = new Person(ps.getFirstName(),
				ps.getLastName(),
				ps.getAddress(),
				ps.getCity(),
				ps.getZip(),
				ps.getPhone(),
				ps.getEmail());
		personService.updatePerson(person);
		return person;
	}
	
	@DeleteMapping(value = "person")
	public Person deletePerson(@RequestBody Person ps) {
		Person person = new Person(ps.getFirstName(),
				ps.getLastName(),
				ps.getAddress(),
				ps.getCity(),
				ps.getZip(),
				ps.getPhone(),
				ps.getEmail());
		personService.deletePerson(person);
		return person;
	}
	
	

}
