package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.service.AlertsService;

import dto.ChildAlertDTO;
import dto.FireStationDTO;
import dto.FloodDTO;
import dto.PersonInfoDTO;

@RestController
public class AlertsController {
	private final AlertsService alertsService;

	public AlertsController(AlertsService alertsService) {
		this.alertsService = alertsService;
	}
	
	@GetMapping(value = "firestation")
	public FireStationDTO getPeopleCoveredByAFireStation(@RequestParam String station_number){
		return alertsService.getPeopleCoveredByAFireStation(station_number);
	}

	@GetMapping(value = "childAlert")
	public List<ChildAlertDTO> getChildrenLivingAt(@RequestParam String address) {
		return alertsService.getChildrenLivingAtAnAddress(address);
	}

	@GetMapping(value = "phoneAlert")
	public List<String> getPhoneNumberOfPeopleCoveredyFireStation(@RequestParam String stationNumber) {
		return alertsService.getPhoneNumberOfPeopleCoveredyFireStation(stationNumber);
	}
	
	@GetMapping(value = "flood")
	public List<FloodDTO> getHomesCoveredByFireStations(@RequestParam String station_number){
		return alertsService.getHomesCoveredByFireStations(station_number);
	}
	
	
	@GetMapping(value = "personInfo")
	public PersonInfoDTO getInfo(@RequestParam String firstName, @RequestParam String lastName) {
		return alertsService.getInfoForPerson(firstName, lastName);
	}

	@GetMapping(value = "communityEmail")
	public List<String> getEmailOfPersonInCity(@RequestParam String city){
		return alertsService.getMailPeopleLivingInCity(city);
	}
}
