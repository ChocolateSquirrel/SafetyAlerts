package com.safetynet.alerts.model;

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

	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "FireStation [station=" + station + ", address=" + address + "]";
	}

	
}
