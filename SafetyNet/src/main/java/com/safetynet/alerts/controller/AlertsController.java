package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.service.AlertsService;

import dto.ChildAlertDTO;
import dto.FireStationDTO;
import dto.PersonInfoDTO;

@RestController
public class AlertsController {
	private final AlertsService alertsService;

	public AlertsController(AlertsService alertsService) {
		this.alertsService = alertsService;
	}

	@GetMapping(value = "firestation/{stationNumber}")
	public FireStationDTO getPeopleCoveredByAFireStation(@PathVariable String stationNumber) throws Exception {
		return alertsService.getPeopleCoveredByAFireStation(stationNumber);
	}

	@GetMapping(value = "childAlert/{address}")
	public List<ChildAlertDTO> getChildrenLivingAt(@PathVariable String address) {
		return alertsService.getChildrenLivingAtAnAddress(address);
	}

	@GetMapping(value = "phoneAlert/{stationNumber}")
	public List<String> getPhoneNumberOfPeopleCoveredyFireStation(@PathVariable String stationNumber) {
		return alertsService.getPhoneNumberOfPeopleCoveredyFireStation(stationNumber);
	}
	
	@GetMapping(value = "communityEmail/{city}")
	public List<String> getEmailOfPersonInCity(@PathVariable String city){
		return alertsService.getMailPeopleLivingInCity(city);
	}
	
	//Voir commentgérer les pathvariables
	@GetMapping(value = "personInfo/")
	public PersonInfoDTO getInfo(@PathVariable String firstName, @PathVariable String lastName) {
		return alertsService.getInfoForPerson(firstName, lastName);
	}

}
