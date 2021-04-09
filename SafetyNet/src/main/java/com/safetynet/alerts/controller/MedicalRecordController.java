package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
	
	private final MedicalRecordService medicalRecordService;

	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		super();
		this.medicalRecordService = medicalRecordService;
	}
	
	@GetMapping(value = "medicalrecords")
	public List<MedicalRecord> getAllMedicalRecords(){
		return medicalRecordService.getAllMedicalRecords();
	}
	
	@PostMapping(value = "medicalrecord")
	public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord mr) {
		MedicalRecord medicalRecord = new MedicalRecord(mr.getFirstName(),
				mr.getLastName(),
				mr.getBirthdate(),
				mr.getMedications(),
				mr.getAllergies());
		medicalRecordService.addMedicalRecord(medicalRecord);
		return medicalRecord;
	}
	
	@PutMapping(value = "medicalrecord")
	public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord mr) {
		MedicalRecord medicalRecord = new MedicalRecord(mr.getFirstName(),
				mr.getLastName(),
				mr.getBirthdate(),
				mr.getMedications(),
				mr.getAllergies());
		medicalRecordService.updateMedicalRecord(medicalRecord);
		return medicalRecord;
	}
	
	@DeleteMapping(value = "medicalrecord")
	public MedicalRecord deleteMedicalRecord(@RequestBody MedicalRecord mr) {
		MedicalRecord medicalRecord = new MedicalRecord(mr.getFirstName(),
				mr.getLastName(),
				mr.getBirthdate(),
				mr.getMedications(),
				mr.getAllergies());
		medicalRecordService.deleteMedicalRecord(medicalRecord);
		return medicalRecord;
	}
	

}
