package com.backend.guestnhouse.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.repository.SeasonRepository;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.services.SeasonService;

@RestController
@RequestMapping("/season")
public class SeasonController {

	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private SeasonService seasonService;
	
	
	//à optimiser si possible
	@PostMapping("/{idRoom}")
	//@PreAuthorize("hasRole('HOST')")
	public String addSeason(@RequestBody Season season,@PathVariable(value="idRoom") String idRoom) {
		Season seasonNameTest=seasonRepository.existsSeasonName(season.getSeasonName(), idRoom);
		Season seasontestDates = seasonRepository.existsSeasonDates(season.getDate_debut(),season.getDate_fin(), idRoom);
		if(season.getDate_debut()!=null && season.getDate_fin()!=null&&(season.getDate_debut().compareTo(season.getDate_fin()) <= 0)) {
			if(seasonNameTest==null && seasontestDates==null && DateExists(season.getDate_debut(), season.getDate_fin(),idRoom)==0) {
				seasonService.addSeason(season, idRoom);
				return "added";
			}else {
				return "not added";
			}
		}else {
			return "fix your dates";
		}
		
	} 
	
	@GetMapping
	public List<Season> listSeasons(){
		return seasonRepository.getSeasons(0);
	}
	
	@DeleteMapping("/{idSeason}")
	public String archiveService(@PathVariable(value="idSeason") String idSeason) {
		return seasonService.archiveSeason(idSeason);
	}
	
	//à optimiser si possible
	@PutMapping("/{idSeason}")
	public String updateRoom(@RequestBody Season season,@PathVariable(value="idSeason") String idSeason) {
		Season updateSeasons=seasonRepository.findById(idSeason).orElse(null);
		if(updateSeasons.getRoomSeasons()!=null) {
			Season seasonNameTest=seasonRepository.existsSeasonNameUpdate(season.getSeasonName(), updateSeasons.getRoomSeasons().getId(),idSeason);
			Season seasontestDates = seasonRepository.existsSeasonDatesUpdate(season.getDate_debut(),season.getDate_fin(), updateSeasons.getRoomSeasons().getId(),idSeason);
			if(season.getDate_debut()!=null && season.getDate_fin()!=null&&(season.getDate_debut().compareTo(season.getDate_fin()) <= 0)) {
				if(seasonNameTest==null && seasontestDates==null && DateExistsUpdate(season.getDate_debut(), season.getDate_fin(),updateSeasons.getRoomSeasons().getId(),idSeason)==0) {
					seasonService.updateSeason(season, idSeason);
					return "updated";
				}else {
					return "not updated";
				}
			}else {
				return "fix your dates";
			}
		}else {
			return "room null";
		}
		
	}
	
	
	
	private int DateExists(Date startDate,Date endDate,String idRoom) {
		int testDate=0;
		for(LocalDate date : getDatesInPeriod(startDate, endDate)) {
			if(seasonRepository.existsSeasonLocalDates(date, idRoom)!=null) {
				testDate++;
				break;
			}
		}
		return testDate;
		
	}
	
	private int DateExistsUpdate(Date startDate,Date endDate,String idRoom,String idSeason) {
		int testDate=0;
		List<LocalDate> DatesInPeriod = getDatesInPeriod(startDate, endDate);
		for(LocalDate date : DatesInPeriod) {
			if(seasonRepository.existsSeasonLocalDatesUpdate(date, idRoom,idSeason)!=null) {
				testDate++;
				break;
			}
		}
		return testDate;
		
	}
	
	private List<LocalDate> getDatesInPeriod(Date startDate, Date endDate) {
	    List<LocalDate> dates = new ArrayList<>();
	    LocalDate start = toLocalDate(startDate);
	    LocalDate end = toLocalDate(endDate);
	    while (!start.equals(end)) {
	      dates.add(start);
	      start = start.plusDays(1);
	    }
	    return dates;
	  }

	private LocalDate toLocalDate(Date date) {
	    Date lDate = new Date(date.getTime());
	    return lDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	  }
}
