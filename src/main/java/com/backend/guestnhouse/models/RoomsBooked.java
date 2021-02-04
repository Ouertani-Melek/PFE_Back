package com.backend.guestnhouse.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms_booked")

public class RoomsBooked {

	@Id
	private String id;
	
	private float priceperday;
	
	private float priceperdayweekend;
	
	private float price;
	
	private Date startdate;
	
	private Date enddate;
	
	String idHouse;
	
	String idUser;
	
	@DBRef
	private Room room;
	
	private int archived;
	
	private int reserved;
	
	private int approved;
	
	private Date created;
	
	private Date modified;
	
	

	public RoomsBooked() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	public String getIdHouse() {
		return idHouse;
	}

	public void setIdHouse(String idHouse) {
		this.idHouse = idHouse;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public float getPriceperday() {
		return priceperday;
	}

	public void setPriceperday(float priceperday) {
		this.priceperday = priceperday;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public float getPriceperdayweekend() {
		return priceperdayweekend;
	}

	public void setPriceperdayweekend(float priceperdayweekend) {
		this.priceperdayweekend = priceperdayweekend;
	}

	
	
	
	
	
	
	
	
}
