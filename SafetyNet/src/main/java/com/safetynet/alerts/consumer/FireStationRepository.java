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

	// ------------------ Methods for add, update or delete a fireStation
	// ----------------- /
	public void saveFireStation(FireStation firestation) {
		firestations.add(firestation);
		dataHandler.save();
	}

	public void updateFireStation(FireStation fireStation) {
		Optional<FireStation> fireStationToUpdate = firestations.stream()
				.filter(fs -> fs.getAddress().equals(fireStation.getAddress())).findFirst();
		if (fireStationToUpdate.isPresent()) {
			FireStation fs = fireStationToUpdate.get();
			fs.setStation(fireStation.getStation());
			dataHandler.save();
		} else
			throw new RuntimeException("No fireStation for address: " + fireStation.getAddress());
	}

	public void delete(FireStation fireStation) {
		Optional<FireStation> fireStationToDelete = firestations.stream()
				.filter(fs -> fs.getAddress().equals(fireStation.getAddress())).findFirst();
		if (fireStationToDelete.isPresent()) {
			FireStation fs = fireStationToDelete.get();
			firestations.remove(fs);
			dataHandler.save();
		} else
			throw new RuntimeException("No fireStation for address: " + fireStation.getAddress());

	}

	public List<FireStation> findAll() {
		return firestations;
	}

	public List<FireStation> findByStationNumber(int stationNumber) {
		List<FireStation> fireStationsFilteredByNumber = firestations.stream()
				.filter(fs -> Integer.parseInt(fs.getStation()) == stationNumber).collect(Collectors.toList());
		if (!fireStationsFilteredByNumber.isEmpty())
			return fireStationsFilteredByNumber;
		else
			throw new RuntimeException("There is no fireStation number " + stationNumber);
	}

	public int findByAddress(String address) {
		Optional<FireStation> optFireStation = firestations.stream().filter(fs -> fs.getAddress().equals(address))
				.findFirst();
		if (optFireStation.isPresent())
			return Integer.parseInt(optFireStation.get().getStation());
		else
			throw new RuntimeException(
					"This address: (" + address + ") is not registered and there is no firestation linked to it");
	}

	public FireStation findAFireStation(String address, String stationNumber) {
		Optional<FireStation> optFireStation = firestations.stream()
				.filter(fs -> fs.getAddress().equals(address) && fs.getStation().equals(stationNumber)).findFirst();
		if (optFireStation.isPresent())
			return optFireStation.get();
		else
			throw new RuntimeException("No fireStation for address: " + address + " and number: " + stationNumber);
	}

}
