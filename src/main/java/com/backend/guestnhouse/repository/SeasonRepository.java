package com.backend.guestnhouse.repository;

import java.util.Date;
import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.guestnhouse.models.Season;

@Repository
public interface SeasonRepository extends MongoRepository<Season, String> {
	
	@Query("{'seasonName' : ?0 , 'archived' : 0, 'roomSeasons.id' : ?1}")
	public Season existsSeasonName(String seasonname,String idRoom);
	
	@Query("{'date_debut': {$lte:?0}, 'date_fin' :{$gte: ?0} , 'archived' : 0, 'roomSeasons.id' : ?1}")
	public Season existsSeasonDate(Date seasondate,String idRoom);
	
	@Query("{'seasonName' : ?0 , 'archived' : 0, 'roomSeasons.id' : ?1,'_id':{$ne : ?2} }")
	public Season existsSeasonNameUpdate(String seasonname,String idRoom,String idSeason);
	
	@Query("{'date_debut': {$lte:?0}, 'date_fin' :{$gte: ?0} , 'archived' : 0, 'roomSeasons.id' : ?1,'_id':{$ne : ?2} }}")
	public Season existsSeasonDatesUpdate(Date seasondateDebut,String idRoom,String idSeason);
	
	@Query("{archived : ?0}")
	public List<Season> getSeasons(int archived);
	
	@Query("{'archived' : ?0,'roomSeasons.id' : ?1}")
	public List<Season> getSeasonsbyRoom(int archived,String idRoom);
	
	@Query("{'archived' : ?0,'roomSeasons.id' : ?1,'_id':{$ne : ?2} }}")
	public List<Season> getSeasonsbyRoomDisable(int archived,String idRoom,String idSeason);
	
	@Query("{'date_debut': {$lte:?0}, 'date_fin' :{$gte: ?1} , 'archived' : 0}")
	public List<Season> checkSeasonReservation(Date startDate,Date endDate);
	


}
