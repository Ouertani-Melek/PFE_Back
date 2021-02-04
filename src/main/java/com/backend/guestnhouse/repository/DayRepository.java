package com.backend.guestnhouse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.guestnhouse.models.Day;

@Repository
public interface DayRepository extends MongoRepository<Day, String>{
		
	@Query("{'date_debut' : ?0 , 'archived' : 0, 'season.id' : ?1}")
	public Day existsDate(Date dateDebut,String idSeason);
	
	@Query("{'dayName' : ?0 , 'archived' : 0, 'season.id' : ?1}")
	public Day existsDay(String dayName,String idSeason);
	
	@Query("{'season.id' : ?0}")
	public List<Day> existsDaybySeasonId(String idSeason);
	
	@Query("{archived : ?0}")
	public List<Day> getAllDays(int archived);
	

}
