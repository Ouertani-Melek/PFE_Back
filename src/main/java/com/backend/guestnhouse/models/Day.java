package com.backend.guestnhouse.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "special_day")
public class Day {

	@Id
	private String id;
	
	private String dayName;
	
	private Date date_debut;
	
	private Date date_fin;
	
	private int special_date;
		
	private float price;
	
	private int archived;

	private Season season;

	public Day() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Day(String id, String dayName, Date date_debut, Date date_fin, int special_date, Season season) {
		super();
		this.id = id;
		this.dayName = dayName;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.special_date = special_date;
		this.season = season;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
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

	public int getSpecial_date() {
		return special_date;
	}

	public void setSpecial_date(int special_date) {
		this.special_date = special_date;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	
	
	
}
