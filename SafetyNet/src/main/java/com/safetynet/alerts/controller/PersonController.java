package com.safetynet.alerts.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonController {
	
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);
	
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping(value = "persons")
	public List<Person> getAllPersons() {
		log.info("Get all people of the file");
		return personService.getAllPersons();
	}
	
	@PostMapping(value = "person")
	public Person addPerson(@Valid @RequestBody Person ps) {
		Person person = new Person(ps.getFirstName(),
				ps.getLastName(),
				ps.getAddress(),
				ps.getCity(),
				ps.getZip(),
				ps.getPhone(),
				ps.getEmail());
		personService.addPerson(person);
		log.info("Add " + person.toString() + " to the file");
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
		if (personService.updatePerson(person)) 
			log.info("Person called : " + person.getFirstName() + " " + person.getLastName() + " was updated");
		else 
			log.error("Person called : " + person.getFirstName() + " " + person.getLastName() + " cannot be updated because this person is not in our file");
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
		if (personService.deletePerson(person))
			log.info("Person called : " + person.getFirstName() + " " + person.getLastName() + " was deleted");
		else 
			log.error("Person called : " + person.getFirstName() + " " + person.getLastName() + " cannot be deleted because this person is not in our file");
		return person;
	}
	
	

}
