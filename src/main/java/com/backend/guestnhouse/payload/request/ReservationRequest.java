package com.backend.guestnhouse.payload.request;

import java.util.List;

public class ReservationRequest {
	
	private List<ReservationDTO> prereservations;
	
	

	public ReservationRequest() {
		super();
	}

	public List<ReservationDTO> getPrereservations() {
		return prereservations;
	}

	public void setPrereservations(List<ReservationDTO> prereservations) {
		this.prereservations = prereservations;
	}
	
	

}
