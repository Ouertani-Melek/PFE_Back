package com.backend.guestnhouse.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "permissions")
public class Permission {

	@Id
	private String id;
	
	private String name;
	
	

	public Permission(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Permission [name=" + name + "]";
	}
	
	
}
