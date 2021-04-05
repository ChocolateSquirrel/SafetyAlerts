package com.safetynet.alerts.model;

import java.util.HashSet;
import java.util.Set;

public class FireStation {
	private int stationNumber;
	private Set<String> addresses = new HashSet<>();
	
	public FireStation(int numberStation) {
		this.stationNumber = numberStation;
	}
	
	public int getNumberStation() {
		return stationNumber;
	}
	
	public FireStation addAddress(String address) {
		addresses.add(address);
		return this;
	}
	
	public Set<String> getAddresses(){
		return addresses;
	}

	@Override
	public String toString() {
		return "FireStation [stationNumber=" + stationNumber + ", addresses=" + addresses + "]";
	}
	
	

}
