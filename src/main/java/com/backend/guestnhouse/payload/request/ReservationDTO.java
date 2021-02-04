package com.backend.guestnhouse.payload.request;

import java.util.Date;
import java.util.List;

import com.backend.guestnhouse.models.RoomsBooked;

public class ReservationDTO {

	private int numberchildren;
	
	private Date startdate;
	
	private Date enddate;
	
	private float price;
	
	private String idUser;
	
	private String idRoom;
	

	public ReservationDTO() {
		super();
	}

	public int getNumberchildren() {
		return numberchildren;
	}

	public void setNumberchildren(int numberchildren) {
		this.numberchildren = numberchildren;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(String idRoom) {
		this.idRoom = idRoom;
	}

	
	
	
}
