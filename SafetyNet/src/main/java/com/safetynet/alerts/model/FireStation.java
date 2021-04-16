package com.safetynet.alerts.model;

import lombok.Data;

@Data
public class FireStation {
	private String station;
	private String address;

	public FireStation() {
		super();
	}
	
	public FireStation(String address, String station) {
		this.address = address;
		this.station = station;
	}

	@Override
	public String toString() {
		return "FireStation [station=" + station + ", address=" + address + "]";
	}



	
}
