package com.safetynet.alerts.consumer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecords;
import com.safetynet.alerts.model.Person;

@Repository
public class SafetyNetRepository {
	private Data data;
	
	public SafetyNetRepository() throws IOException {
		data = new Data("C:\\Users\\Marie\\Git\\SafetyAlerts\\SafetyNet\\src\\main\\resources/data.json");
	}
	
	public List<Person> findAllPersonn(){
		return data.getPersonsList();
	}
	
	public List<MedicalRecords> findAllMedicalRecords(){
		return data.getMedicalRecordsList();
	}
	
	public Map<Integer, FireStation> findAllStation(){
		return data.getFireStationsMap();
	}
}
