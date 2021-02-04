package com.backend.guestnhouse.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "season")
public class Season {

	@Id
	private String id;
	
	@NotBlank
	private String seasonName;
	
	private Date date_debut;
	
	private Date date_fin;
	
	private int archived;
	
	private float normalPrice;
	
	private float weekendPrice;
	
	@DBRef
	private Room roomSeasons;
	
	public Season() {
		super();
	}

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public Room getRoomSeasons() {
		return roomSeasons;
	}

	public void setRoomSeasons(Room roomSeasons) {
		this.roomSeasons = roomSeasons;
	}
	
	

	public float getNormalPrice() {
		return normalPrice;
	}



	public void setNormalPrice(float normalPrice) {
		this.normalPrice = normalPrice;
	}



	public float getWeekendPrice() {
		return weekendPrice;
	}



	public void setWeekendPrice(float weekendPrice) {
		this.weekendPrice = weekendPrice;
	}



	public Season(String id, String seasonName, Date date_debut, Date date_fin, int archived, Room roomSeasons) {
		super();
		this.id = id;
		this.seasonName = seasonName;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.archived = archived;
		this.roomSeasons = roomSeasons;
	}



	public Season(@NotBlank String seasonName, Room roomSeasons) {
		super();
		this.seasonName = seasonName;
		this.roomSeasons = roomSeasons;
	}
	
	

	
	
	
}
