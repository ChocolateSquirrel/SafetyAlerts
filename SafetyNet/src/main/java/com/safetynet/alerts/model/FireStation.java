package com.safetynet.alerts.model;

public class FireStation {
	private int stationNumber;
	private String address;

	public FireStation() {
		super();
	}

	public FireStation(int stationNumber, String address) {
		this.stationNumber = stationNumber;
		this.address = address;
	}

	/**
	 * @return the stationNumber
	 */
	public int getStationNumber() {
		return stationNumber;
	}

	/**
	 * @param stationNumber the stationNumber to set
	 */
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
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
		return "FireStation [stationNumber=" + stationNumber + ", address=" + address + "]";
	}

}
