package com.backend.guestnhouse.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class Review {

	@Id
	private String id;
	
	private float rating;
	
	private String comment;
	
	private Date created_at;
	
	private Date modified_at;
	
	private int archived;
	
	private House house;
	
	private User user;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(String id, float rating, String comment, Date created_at, Date modified_at, int archived, House house,
			User user) {
		super();
		this.id = id;
		this.rating = rating;
		this.comment = comment;
		this.created_at = created_at;
		this.modified_at = modified_at;
		this.archived = archived;
		this.house = house;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getModified_at() {
		return modified_at;
	}

	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
}
