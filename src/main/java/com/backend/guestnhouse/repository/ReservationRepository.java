package com.backend.guestnhouse.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.guestnhouse.models.Booking;

@Repository
public interface ReservationRepository  extends MongoRepository<Booking, String>{

	@Query("{'startdate': {$lte:?0}, 'enddate' :{$gte: ?0} , 'archived' : 0, 'room.id' : ?1}")
	public Booking checkreservations(Date reservationDate,String idRoom);
	
}
