package com.safetynet.alerts.consumer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	// ------------------ Methods for add, update or delete a fireStation ----------------- /
	public void saveFireStation(FireStation firestation) {
		firestations.add(firestation);
		dataHandler.save();
	}

	public void updateFireStation(FireStation fireStation) {
		firestations.stream().filter(fs -> fs.getAddress().equals(fireStation.getAddress()))
		.findAny().ifPresent(fs -> {
			fs.setStation(fireStation.getStation());
			dataHandler.save();
		});

	}

	public void delete(FireStation fireStation) {
		firestations.stream()
		.filter(fs -> fs.getAddress().equals(fireStation.getAddress())
				&& fs.getStation().equals(fireStation.getStation()))
		.findAny().ifPresent(f -> {
			firestations.remove(f);
			dataHandler.save();
		});

	}

	public List<FireStation> findAll() {
		return firestations;
	}

	public List<FireStation> findByStationNumber(int stationNumber) {
		List<FireStation> fireStationsFilteredByNumber = firestations.stream()
				.filter(fs -> Integer.parseInt(fs.getStation()) == stationNumber).collect(Collectors.toList());
		return fireStationsFilteredByNumber;
	}

	public int findByAddress(String address) {
		Optional<FireStation> optFireStation = firestations.stream().filter(fs -> fs.getAddress().equals(address)).findFirst();
		if (optFireStation.isPresent())
			return Integer.parseInt(optFireStation.get().getStation());
		else 
			throw new RuntimeException("This address is not registered and there is no firestation linked to it");
	}

}
