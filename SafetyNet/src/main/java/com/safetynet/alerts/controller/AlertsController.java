package com.safetynet.alerts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value ="firestation")
	public FireStationDTO getPeopleCoveredByAFireStation(@RequestParam String stationNumber) throws Exception {
		List<Person> people = fireStationService.getPeopleCoveredByAFireStation(Integer.parseInt(stationNumber));
		List<Person> adults = personService.getAdults(people);
		List<Person> children = personService.getChildren(people);
		List<FireStationDTO.Person> person1 = people.stream().map(p -> 
			new FireStationDTO.Person(p.getFirstName(), p.getLastName(), p.getAddress(), p.getPhone())
		).collect(Collectors.toList());
		FireStationDTO fireDto = new FireStationDTO(person1, adults.size(), children.size());
		return fireDto;
	}
	
	@GetMapping(value = "childAlert")
	public List<ChildAlertDTO> getChildrenLivingAt(@RequestParam String address) {
		List<Person> children = personService.getChildren(personService.getPersonByAddress(address));
		List<ChildAlertDTO> childAlertList = new ArrayList<>();
		for (Person child : children) {
			String birthdate = personService.getBirthdate(child);
			ChildAlertDTO childAlert = new ChildAlertDTO(child.getFirstName(), child.getLastName(), child.getAge(birthdate), personService.getPeopleLivingAtTheSameAddress(child));
			childAlertList.add(childAlert);
		}
		return childAlertList;
	}
	
	@GetMapping(value = "phoneAlert/")
	public List<String> getPhoneNumberOfPeopleCoveredyFireStation(@RequestParam String firestation_number) {
		List<Person> people = fireStationService.getPeopleCoveredByAFireStation(Integer.parseInt(firestation_number));
		List<String> phoneList = new ArrayList<>();
		for (Person person : people) {
			if (phoneList.contains(person.getPhone())) continue;
			phoneList.add(person.getPhone());
		}
		return phoneList;
	}
	
	

}
