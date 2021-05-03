package com.safetynet.alerts.dto;

import java.util.List;

import lombok.Data;

@Data
public class FloodDTO {
	private int stationNumber;
	List<FloodDTOInfo> infoFloodList;
	
	public FloodDTO(int stationNumber, List<FloodDTOInfo> infoFloodList) {
		super();
		this.stationNumber = stationNumber;
		this.infoFloodList = infoFloodList;
	}
	
	

}
