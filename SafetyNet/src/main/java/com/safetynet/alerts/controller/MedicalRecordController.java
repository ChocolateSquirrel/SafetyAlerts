package com.safetynet.alerts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
	
	private final MedicalRecordService medicalRecordService;

	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		super();
		this.medicalRecordService = medicalRecordService;
	}
	
	

}
