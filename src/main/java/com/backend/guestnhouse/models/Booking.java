package com.backend.guestnhouse.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booking")
public class Booking {

	@Id
	private String id;
	
	private Date startdate;
	
	private Date enddate;
	
	private float price;
	
	private String status;
	
	private Date created;
	
	private Date modified;
	
	private int archived;
	
	@DBRef
	private User user;
	
	List<RoomsBooked> roomsbookedcompleted;

	public Booking() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	

	public List<RoomsBooked> getRoomsbookedcompleted() {
		return roomsbookedcompleted;
	}

	public void setRoomsbookedcompleted(List<RoomsBooked> roomsbookedcompleted) {
		this.roomsbookedcompleted = roomsbookedcompleted;
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

	
	
	

	
	
}
