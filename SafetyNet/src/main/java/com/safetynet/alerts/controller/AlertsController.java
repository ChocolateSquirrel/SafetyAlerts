package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.service.AlertsService;

import dto.ChildAlertDTO;
import dto.FireStationDTO;

@RestController
public class AlertsController {
	private final AlertsService alertsService;

	public AlertsController(AlertsService alertsService) {
		this.alertsService = alertsService;
	}

	@GetMapping(value = "firestation")
	public FireStationDTO getPeopleCoveredByAFireStation(@RequestParam String stationNumber) throws Exception {
		return alertsService.getPeopleCoveredByAFireStation(stationNumber);
	}

	@GetMapping(value = "childAlert")
	public List<ChildAlertDTO> getChildrenLivingAt(@RequestParam String address) {
		return alertsService.getChildrenLivingAtAnAddress(address);
	}

	@GetMapping(value = "phoneAlert")
	public List<String> getPhoneNumberOfPeopleCoveredyFireStation(@RequestParam String firestation_number) {
		return alertsService.getPhoneNumberOfPeopleCoveredyFireStation(firestation_number);
	}
	
	@GetMapping(value = "communityEmail")
	public List<String> getEmailOfPersonInCity(@RequestParam String city){
		return alertsService.getMailPeopleLivingInCity(city);
	}

}
