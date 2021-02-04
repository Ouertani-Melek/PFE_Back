package com.backend.guestnhouse.payload.request;

import com.backend.guestnhouse.models.Room;

public class RoomDTO {

	
	private Room room;
	
	private float priceperdayweekend;
	
	private float priceperday;
	
	private float totalpriceroom;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public float getPriceperdayweekend() {
		return priceperdayweekend;
	}

	public void setPriceperdayweekend(float priceperdayweekend) {
		this.priceperdayweekend = priceperdayweekend;
	}

	public float getPriceperday() {
		return priceperday;
	}

	public void setPriceperday(float priceperday) {
		this.priceperday = priceperday;
	}
	
	

	public float getTotalpriceroom() {
		return totalpriceroom;
	}

	public void setTotalpriceroom(float totalpriceroom) {
		this.totalpriceroom = totalpriceroom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(priceperday);
		result = prime * result + Float.floatToIntBits(priceperdayweekend);
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o){
	    if (o instanceof RoomDTO){
	    	RoomDTO temp = (RoomDTO)o;
	        if (this.room.equals(temp.getRoom()))
	            return true;
	    }
	    return false;
	}
	
	
}
