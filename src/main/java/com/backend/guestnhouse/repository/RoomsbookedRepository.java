package com.backend.guestnhouse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backend.guestnhouse.models.RoomsBooked;
import com.backend.guestnhouse.models.Booking;

public interface RoomsbookedRepository extends CrudRepository<RoomsBooked, String> {

	@Query("{'startdate' : ?0,'enddate' : ?1 , 'archived' : 0,'reserved':0, 'room.id' : ?2,'idUser':?3}")
	public RoomsBooked existsDatesroom(Date startdate,Date enddate,String idRoom,String idUser);
	
	@Query("{'startdate' : ?0,'enddate' : ?1 , 'archived' : 0,'reserved':0,'approved': 1,'idUser':?2, 'idHouse' : ?3}")
	public List<RoomsBooked> getPrereservationbyIduser(Date startdate,Date enddate,String idUser,String idHouse);
	
	@Query("{'startdate': {$gte:?0}, 'enddate' :{$lte: ?1} , 'archived' : 0,'reserved': 1, 'room.id' : ?2}")
	public Booking checkreservations(Date startdate,Date enddate,String idRoom);
	
	@Query("{'startdate': {$gte:?0}, 'enddate' :{$lte: ?1}  , 'archived' : 0,'reserved': 0, 'idHouse' : ?2}")
	public List<RoomsBooked> getPrereservationbyHouse(Date startdate,Date enddate,String idhouse);
	
	@Query("{'startdate': {$gte:?0}, 'enddate' :{$lte: ?1}  , 'archived' : 0,'reserved': 0,'room.id':?2, 'idHouse' : ?3,'idUser':{$ne : ?4}}")
	public RoomsBooked getroomsbookedcanceledbyHouse(Date startdate,Date enddate,String idRoom,String idhouse,String idUser);
	
	@Query("{'startdate': {$lte:?0}, 'enddate' :{$gte: ?0} , 'archived' : 0,'reserved': 0,'approved':1, 'room.id':?1, 'idHouse' : ?2,'idUser':{$ne : ?3}}")
	public RoomsBooked existsRoomsbookedDate(Date seasondate,String idRoom,String idhouse,String idUser);
	
	@Query("{'startdate': {$lte:?0}, 'enddate' :{$gte: ?0} , 'archived' : 0,'reserved': 0,'approved':1, 'idHouse' : ?1}")
	public List<RoomsBooked> existsRoomsbookedDateHouse(Date seasondate,String idhouse);
	
	
}
