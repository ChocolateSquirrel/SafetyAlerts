package com.safetynet.alerts.consumer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {
	private final DataHandler dataHandler;
	private List<MedicalRecord> medicalRecords;

	public MedicalRecordRepository(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
		medicalRecords = dataHandler.getData().getMedicalrecords();
	}

}
