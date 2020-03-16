package com.backend.guestnhouse.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "room")
public class Room {

	@Id
	private String id;
	
	private String roomName;
	
	private String roomType;
	
	private String description;
	
	private String roomImages;
	
	private float priceRoom;
	
	private int archived;




	public Room(String id, String roomName, String roomType, String description, String roomImages, float priceRoom,
			int archived) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomType = roomType;
		this.description = description;
		this.roomImages = roomImages;
		this.priceRoom = priceRoom;
		this.archived = archived;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomImages() {
		return roomImages;
	}

	public void setRoomImages(String roomImages) {
		this.roomImages = roomImages;
	}

	public float getPriceRoom() {
		return priceRoom;
	}

	public void setPriceRoom(float priceRoom) {
		this.priceRoom = priceRoom;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	
	
	
	
	
}
