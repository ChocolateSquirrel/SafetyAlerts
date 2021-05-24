package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MedicalRecordController {
	
	private final MedicalRecordService medicalRecordService;

	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		super();
		this.medicalRecordService = medicalRecordService;
	}
	
	@GetMapping(value = "medicalrecords")
	public List<MedicalRecord> getAllMedicalRecords(){
		log.info("Request: GET /medicalrecords");
		List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
		log.info("Response: " + JsonStream.serialize(medicalRecords));
		return medicalRecords;
	}
	
	@PostMapping(value = "medicalrecord")
	public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord mr) {
		log.info("Request: POST /medicalrecord");
		MedicalRecord medicalRecord = new MedicalRecord(mr.getFirstName(),
				mr.getLastName(),
				mr.getBirthdate(),
				mr.getMedications(),
				mr.getAllergies());
		medicalRecordService.addMedicalRecord(medicalRecord);
		log.info("Response: " + JsonStream.serialize(medicalRecord));
		return medicalRecord;
	}
	
	@PutMapping(value = "medicalrecord")
	public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord mr) {
		log.info("Request: PUT /medicalrecord");
		MedicalRecord medicalRecord = new MedicalRecord(mr.getFirstName(),
				mr.getLastName(),
				mr.getBirthdate(),
				mr.getMedications(),
				mr.getAllergies());
		medicalRecordService.updateMedicalRecord(medicalRecord);
		log.info("Response: " + JsonStream.serialize(medicalRecord));
		return medicalRecord;
	}
	
	@DeleteMapping(value = "medicalrecord")
	public MedicalRecord deleteMedicalRecord(@RequestBody MedicalRecord mr) {
		log.info("Request: DELETE /medicalrecord");
		MedicalRecord medicalRecord = new MedicalRecord(mr.getFirstName(),
				mr.getLastName(),
				mr.getBirthdate(),
				mr.getMedications(),
				mr.getAllergies());
		medicalRecordService.deleteMedicalRecord(medicalRecord);
		log.info("Response: " + JsonStream.serialize(medicalRecord));
		return medicalRecord;
	}
	

}
