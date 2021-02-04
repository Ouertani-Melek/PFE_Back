package com.backend.guestnhouse.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Document(collection = "rooms")

public class Room {

    @Id
    private String id;

    private String roomName;

    private boolean smokingPolicy;

    private int roomSurface;

    private float priceRoom;

    private List<String> images;

    private Date created;

    private Date modified;

    private int archived;

    @DBRef
    private House house;

    @DBRef
    private Set<Amenities> amenities;

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

    public Set<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenities> amenities) {
        this.amenities = amenities;
    }

    public boolean isSmokingPolicy() {
        return smokingPolicy;
    }

    public void setSmokingPolicy(boolean smokingPolicy) {
        this.smokingPolicy = smokingPolicy;
    }

    public int getRoomSurface() {
        return roomSurface;
    }

    public void setRoomSurface(int roomSurface) {
        this.roomSurface = roomSurface;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomName == null) ? 0 : roomName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (roomName == null) {
			if (other.roomName != null)
				return false;
		} else if (!roomName.equals(other.roomName))
			return false;
		return true;
	}
    
}
