package com.backend.guestnhouse.controllers;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.backend.guestnhouse.repository.SeasonRepository;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.payload.response.MessageResponse;
import com.backend.guestnhouse.services.DayService;
import com.backend.guestnhouse.services.SeasonService;

@RestController
@RequestMapping("/season")
public class SeasonController {

	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private SeasonService seasonService;
	
	@Autowired
	private DayService dayService;
	
	
	//à optimiser si possible
	@PostMapping("/{idRoom}")
	//@PreAuthorize("hasRole('HOST')")
	public ResponseEntity<?> addSeason(@RequestBody @Valid Season season,@PathVariable(value="idRoom") String idRoom) {
		if(season.getDate_debut()!=null && season.getDate_fin()!=null && (season.getDate_debut().compareTo(season.getDate_fin()) <=  0)) {
			Calendar calendarstart = Calendar.getInstance();
			calendarstart.setTime(season.getDate_debut());
			calendarstart.add(Calendar.HOUR_OF_DAY, 14);
		    Calendar calendarend = Calendar.getInstance();
		    calendarend.setTime(season.getDate_fin());
		    calendarend.add(Calendar.HOUR_OF_DAY, 11);
		    season.setDate_debut(calendarstart.getTime());
		    season.setDate_fin(calendarend.getTime());
			Season seasontestName=seasonRepository.existsSeasonName(season.getSeasonName(),idRoom);
			Season seasontestDateDebut = seasonRepository.existsSeasonDate(season.getDate_debut(), idRoom);
			Season seasontestDateFin = seasonRepository.existsSeasonDate(season.getDate_fin(), idRoom);
				if(seasontestName==null && seasontestDateDebut==null && seasontestDateFin==null && DateExists(season.getDate_debut(), season.getDate_fin(),idRoom)==0) {
					return ResponseEntity.accepted().body(seasonService.addSeason(season, idRoom));
				}else if(seasontestDateDebut!=null ) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date Invalid");
				}else if (seasontestDateFin!=null) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date Invalid");
				}
				else if(DateExists(season.getDate_debut(), season.getDate_fin(),idRoom)>0) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dates in range");
				}else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name Invalid");
				}
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "check your dates");
				
		
		
		
	} 
	
	@GetMapping("/seasonbyRoom/{idRoom}")
	public List<Season> listSeasons(@PathVariable(value="idRoom") String idRoom){
		return seasonRepository.getSeasonsbyRoom(0, idRoom);
	}
	
	@GetMapping("/{idSeason}")
	public Season getSeasonbyId(@PathVariable(value="idSeason") String idSeason){
		return seasonRepository.findById(idSeason).orElse(null);
	}
	
	@DeleteMapping("/{idSeason}")
	public String archiveService(@PathVariable(value="idSeason") String idSeason) {
		return seasonService.archiveSeason(idSeason);
	}
	
	//à optimiser si possible
	@PutMapping("/{idSeason}")
	public ResponseEntity<?> updateRoom(@RequestBody @Valid Season season,@PathVariable(value="idSeason") String idSeason) {
		Season updateSeasons=seasonRepository.findById(idSeason).orElse(null);
		if(updateSeasons!=null && updateSeasons.getRoomSeasons()!=null && season.getDate_debut()!=null && season.getDate_fin()!=null && (season.getDate_debut().compareTo(season.getDate_fin()) <= 0)) {
			Calendar calendarstart = Calendar.getInstance();
			calendarstart.setTime(season.getDate_debut());
			calendarstart.add(Calendar.HOUR_OF_DAY, 14);
		    Calendar calendarend = Calendar.getInstance();
		    calendarend.setTime(season.getDate_fin());
		    calendarend.add(Calendar.HOUR_OF_DAY, 11);
		    season.setDate_debut(calendarstart.getTime());
		    season.setDate_fin(calendarend.getTime());
			Season seasontestName=seasonRepository.existsSeasonNameUpdate(season.getSeasonName(),updateSeasons.getRoomSeasons().getId(),idSeason);
			Season seasontestDateDebut = seasonRepository.existsSeasonDatesUpdate(season.getDate_debut(), updateSeasons.getRoomSeasons().getId(),idSeason);
			Season seasontestDateFin = seasonRepository.existsSeasonDatesUpdate(season.getDate_fin(), updateSeasons.getRoomSeasons().getId(),idSeason);
				if(seasontestName==null  && seasontestDateDebut==null && seasontestDateFin==null && DateExistsUpdate(season.getDate_debut(), season.getDate_fin(),updateSeasons.getRoomSeasons().getId(),idSeason)==0) {
					return ResponseEntity.accepted().body(seasonService.updateSeason(season, idSeason));
				}
				else if(seasontestDateDebut!=null ) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date Invalid");
				}else if (seasontestDateFin!=null) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date Invalid");
				}
				else if(DateExistsUpdate(season.getDate_debut(), season.getDate_fin(),updateSeasons.getRoomSeasons().getId(),idSeason)>0) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dates in range");
				}else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name Invalid");
				}
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "check your dates");

}
	
	
	
	private int DateExists(Date startDate,Date endDate,String idRoom) {
		int testDate=0;
			List<Date> datesBetween = dayService.getDatesBetween(startDate, endDate);
			for(Date date : datesBetween) {
				if(seasonRepository.existsSeasonDate(date, idRoom)!=null) {
					
					testDate++;
					break;
				}
			}
		
		return testDate;
		
	}
	
	private int DateExistsUpdate(Date startDate,Date endDate,String idRoom,String idSeason) {
		int testDate=0;
		List<Date> datesBetween = dayService.getDatesBetween(startDate, endDate);
		for(Date date : datesBetween) {
			if(seasonRepository.existsSeasonDatesUpdate(date, idRoom,idSeason)!=null) {
				testDate++;
				break;
			}
		}
		return testDate;
		
	}
	
}
