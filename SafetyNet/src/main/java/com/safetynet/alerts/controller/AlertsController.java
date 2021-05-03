package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.FireStationDTO;
import com.safetynet.alerts.dto.FloodDTO;
import com.safetynet.alerts.dto.FloodDTOInfo;
import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.service.AlertsService;

@RestController
public class AlertsController {
	private final AlertsService alertsService;

	public AlertsController(AlertsService alertsService) {
		this.alertsService = alertsService;
	}
	
	@GetMapping(value = "firestation")
	public FireStationDTO getPeopleCoveredByAFireStation(@RequestParam String stationNumber){
		return alertsService.getPeopleCoveredByAFireStation(stationNumber);
	}

	@GetMapping(value = "childAlert")
	public List<ChildAlertDTO> getChildrenLivingAt(@RequestParam String address) {
		return alertsService.getChildrenLivingAtAnAddress(address);
	}

	@GetMapping(value = "phoneAlert")
	public List<String> getPhoneNumberOfPeopleCoveredyFireStation(@RequestParam String firestation) {
		return alertsService.getPhoneNumberOfPeopleCoveredyFireStation(firestation);
	}
	
	@GetMapping(value = "fire")
	public FireDTO getPeopleLivingAtThisAddress(@RequestParam String address) {
		return alertsService.getPeopleLivingAtThisAddress(address);
	}
	
	@GetMapping(value = "flood/stations")
	public List<FloodDTO> getHomesCoveredByDiversesFireStations(@RequestParam List<String> stations){
		return alertsService.getHomesCoveredByDiversesFireStations(stations);
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
