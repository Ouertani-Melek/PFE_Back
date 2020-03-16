package com.backend.guestnhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.repository.SeasonRepository;
import com.backend.guestnhouse.repository.UserRepository;

@Service
public class SeasonService {

	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	public Boolean addSeason(Season season,String idRoom) {
		Room room=roomRepository.findById(idRoom).orElse(null);
		if(room!=null) {
			season.setRoomSeasons(room);
			seasonRepository.save(season);
			return true;
		}
		return false;
	}
	
	
	public String archiveSeason(String idSeason) {
		Season season=seasonRepository.findById(idSeason).orElse(null);
		season.setArchived(1);
		/*for(Day day : season.getDays()) {
			day.setArchived(1);
		}*/
		seasonRepository.save(season);
		return ("archived");	
	}
	
	public Season updateSeason(Season season,String idSeason) {
		Season updateSeason=seasonRepository.findById(idSeason).orElse(null);
		updateSeason.setSeasonName(season.getSeasonName());
		updateSeason.setDate_debut(season.getDate_debut());
		updateSeason.setDate_fin(season.getDate_fin());
		seasonRepository.save(updateSeason);
		return updateSeason;
	}
}
