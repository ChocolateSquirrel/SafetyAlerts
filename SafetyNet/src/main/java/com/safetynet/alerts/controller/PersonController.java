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

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PersonController {
	
	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping(value = "persons")
	public List<Person> getAllPersons() {
		log.info("Request: GET /persons");
		List<Person> persons = personService.getAllPersons();
		log.info("Response: " + JsonStream.serialize(persons));
		return persons;
	}
	
	@PostMapping(value = "person")
	public Person addPerson(@Valid @RequestBody Person ps) {
		log.info("Request: POST /person");
		Person person = new Person(ps.getFirstName(),
				ps.getLastName(),
				ps.getAddress(),
				ps.getCity(),
				ps.getZip(),
				ps.getPhone(),
				ps.getEmail());
		personService.addPerson(person);
		log.info("Response: " + JsonStream.serialize(person));
		return person;
	}
	
	@PutMapping(value = "person")
	public Person updatePerson(@RequestBody Person ps) {
		log.info("Request: PUT /person");
		Person person = new Person(ps.getFirstName(),
				ps.getLastName(),
				ps.getAddress(),
				ps.getCity(),
				ps.getZip(),
				ps.getPhone(),
				ps.getEmail());
		personService.updatePerson(person);
		log.info("Response: " + JsonStream.serialize(person));
		return person;
	}
	
	@DeleteMapping(value = "person")
	public Person deletePerson(@RequestBody Person ps) {
		log.info("Request: DELETE /person");
		Person person = new Person(ps.getFirstName(),
				ps.getLastName(),
				ps.getAddress(),
				ps.getCity(),
				ps.getZip(),
				ps.getPhone(),
				ps.getEmail());
		personService.deletePerson(person);
		log.info("Response: " + JsonStream.serialize(person));
		return person;
	}
	
	

}
