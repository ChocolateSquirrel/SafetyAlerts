package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jsoniter.JsonIterator;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.FireStationService;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonService;

import dto.ChildAlertDTO;
import dto.FireStationDTO;

@RestController
public class AlertsController {
	private final FireStationService fireStationService;
	private final MedicalRecordService medicalRecordService;
	private final PersonService personService;

	public AlertsController(FireStationService fireStationService, MedicalRecordService medicalRecordService,
			PersonService personService) {
		super();
		this.fireStationService = fireStationService;
		this.medicalRecordService = medicalRecordService;
		this.personService = personService;
	}
	
	@GetMapping(value ="firestation/{stationNumber}")
	public FireStationDTO getPeopleCoveredByAFireStation(@PathVariable String stationNumber) throws Exception {
		List<Person> people = fireStationService.getPeopleCoveredByAFireStation(Integer.parseInt(stationNumber));
		List<Person> adults = personService.getAdults(people);
		List<Person> children = personService.getChildren(people);
//		SimpleBeanPropertyFilter filtre = SimpleBeanPropertyFilter.serializeAllExcept("city", "zip", "email");
//		FilterProvider listFiltre = new SimpleFilterProvider().addFilter("personFiltre", filtre);
//		ObjectMapper om = new ObjectMapper();
//		om.setFilterProvider(listFiltre);
//		List<Person> personFiltre = new ArrayList<>();
//		for (Person person : people) {
//			personFiltre.add(JsonIterator.deserialize(om.writeValueAsString(person), Person.class));
//		}
		FireStationDTO fireDto = new FireStationDTO(people, adults.size(), children.size());
		return fireDto;
	}
	
	@GetMapping(value = "childAlert/{address}")
	public List<ChildAlertDTO> getChildrenLivingAt(@PathVariable String address) {
		List<Person> children = personService.getChildren(personService.getPersonByAddress(address));
		List<ChildAlertDTO> childAlertList = new ArrayList<>();
		for (Person child : children) {
			String birthdate = personService.getBirthdate(child);
			ChildAlertDTO childAlert = new ChildAlertDTO(child.getFirstName(), child.getLastName(), child.getAge(birthdate), personService.peopleLivingAtTheSameAddress(child));
			childAlertList.add(childAlert);
		}
		return childAlertList;
	}
	
	

}
