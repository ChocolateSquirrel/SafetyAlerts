package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.dto.ChildAlertDTO;
import com.safetynet.alerts.dto.FireDTO;
import com.safetynet.alerts.dto.FireStationDTO;
import com.safetynet.alerts.dto.FloodDTO;
import com.safetynet.alerts.dto.FloodDTOInfo;
import com.safetynet.alerts.dto.PersonInfoDTO;
import com.safetynet.alerts.service.AlertsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AlertsController {
	private final AlertsService alertsService;

	public AlertsController(AlertsService alertsService) {
		this.alertsService = alertsService;
	}
	
	@GetMapping(value = "firestation")
	public FireStationDTO getPeopleCoveredByAFireStation(@RequestParam String stationNumber){
		log.info("Request: GET /firestation");
		FireStationDTO fireStationDTO = alertsService.getPeopleCoveredByAFireStation(stationNumber);
		log.info("Response: " + JsonStream.serialize(fireStationDTO));
		return fireStationDTO;
	}

	@GetMapping(value = "childAlert")
	public List<ChildAlertDTO> getChildrenLivingAt(@RequestParam String address) {
		log.info("Request: GET /childAlert");
		List<ChildAlertDTO> children = alertsService.getChildrenLivingAtAnAddress(address);
		log.info("Response: " + JsonStream.serialize(children));
		return children;
	}

	@GetMapping(value = "phoneAlert")
	public List<String> getPhoneNumberOfPeopleCoveredyFireStation(@RequestParam String firestation) {
		log.info("Request: GET /phoneAlert");
		List<String> phone = alertsService.getPhoneNumberOfPeopleCoveredyFireStation(firestation);
		log.info("Response: " + JsonStream.serialize(phone));
		return phone;
	}
	
	@GetMapping(value = "fire")
	public FireDTO getPeopleLivingAtThisAddress(@RequestParam String address) {
		log.info("Request: GET /fire");
		FireDTO fire = alertsService.getPeopleLivingAtThisAddress(address);
		log.info("Response: " + JsonStream.serialize(fire));
		return fire;
	}
	
	@GetMapping(value = "flood/stations")
	public List<FloodDTO> getHomesCoveredByDiversesFireStations(@RequestParam List<String> stations){
		log.info("Request: GET /flood/stations");
		List<FloodDTO> flood = alertsService.getHomesCoveredByDiversesFireStations(stations);
		log.info("Response: " + JsonStream.serialize(flood));
		return flood;
	}
	
	
	@GetMapping(value = "personInfo")
	public PersonInfoDTO getInfo(@RequestParam String firstName, @RequestParam String lastName) {
		log.info("Request: GET /personInfo");
		PersonInfoDTO info = alertsService.getInfoForPerson(firstName, lastName);
		log.info("Response: " + JsonStream.serialize(info));
		return info;
	}

	@GetMapping(value = "communityEmail")
	public List<String> getEmailOfPersonInCity(@RequestParam String city){
		log.info("Request: GET /communityEmail");
		List<String> community = alertsService.getMailPeopleLivingInCity(city);
		log.info("Response: " + JsonStream.serialize(community));
		return community;
	}
}
