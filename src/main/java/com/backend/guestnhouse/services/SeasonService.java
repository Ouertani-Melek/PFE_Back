package com.backend.guestnhouse.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.models.Day;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.repository.DayRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.repository.SeasonRepository;

@Service
public class SeasonService {

	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private DayRepository dayRepository;
	
	public Season addSeason(Season season,String idRoom) {
		Room room=roomRepository.findById(idRoom).orElse(null);
		if(room!=null) {
			season.setRoomSeasons(room);
			seasonRepository.save(season);
			return season;
		}
		return null;
	}
	
	
	public String archiveSeason(String idSeason) {
		Season season=seasonRepository.findById(idSeason).orElse(null);
		List<Day> days = dayRepository.existsDaybySeasonId(idSeason);
		if(season!=null) {
			season.setArchived(1);
			for(Day day : days) {
				day.setArchived(1);
				dayRepository.save(day);
			}
			seasonRepository.save(season);
			return ("archived");	
		}else {
			return "no season to be archived";

		}
		
	}
	
	public Season updateSeason(Season season,String idSeason) {
		Season updateSeason=seasonRepository.findById(idSeason).orElse(null);
		if(updateSeason!=null) {
			updateSeason.setSeasonName(season.getSeasonName());
			updateSeason.setDate_debut(season.getDate_debut());
			updateSeason.setDate_fin(season.getDate_fin());
			updateSeason.setNormalPrice(season.getNormalPrice());
			updateSeason.setWeekendPrice(season.getWeekendPrice());
			seasonRepository.save(updateSeason);
			return updateSeason;
		}
		return null;
		
		
	}
}
