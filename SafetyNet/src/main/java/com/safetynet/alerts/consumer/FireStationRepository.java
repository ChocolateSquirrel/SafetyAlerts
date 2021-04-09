package com.safetynet.alerts.consumer;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.FireStation;

@Repository
public class FireStationRepository {
	private final DataHandler dataHandler;
	private List<FireStation> firestations;
	
	public FireStationRepository(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
		firestations = dataHandler.getData().getFirestations();
	}
	
	
	

}
