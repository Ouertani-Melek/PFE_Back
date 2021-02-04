package com.backend.guestnhouse.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.models.Day;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.repository.DayRepository;
import com.backend.guestnhouse.repository.SeasonRepository;
import com.backend.guestnhouse.services.DayService;

@RestController
@RequestMapping("/day")
public class DayController {

	@Autowired
	private DayRepository dayRepository;
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private DayService dayService;
	
	@PostMapping("/specialDay/{idSeason}")
	public String addSpecialDay(@RequestBody Day day,@PathVariable(value="idSeason") String idSeason) {
		return dayService.addSpecialDay(day,idSeason);

	}
	/*
	 * A optimiser si possible
	 */
	@GetMapping("/{idRoom}")
	public List<HashMap<String, Object>> getAllDays(@PathVariable(value="idRoom") String idRoom) {
		Calendar calendar = Calendar.getInstance();
		List<HashMap<String,Object>> daysPrices= new ArrayList<HashMap<String,Object>>();
		List<Day> specialDays= dayRepository.getAllDays(0);
		List<Season> seasons = seasonRepository.getSeasonsbyRoom(0, idRoom);
		for(Season season : seasons) {
			List<Date> datesBetween=dayService.getDatesBetween(season.getDate_debut(),season.getDate_fin());
			for(Date date : datesBetween) {
				calendar.setTime(date);
				int day= calendar.get(Calendar.DAY_OF_WEEK);
				HashMap<String, Object> map = new HashMap<>();
				map.put("startdate",date);
				map.put("endate",date);
				if(day==1 || day==7) {
					map.put("dayName","Weekend");
					map.put("price",season.getWeekendPrice());
				}else {
					map.put("dayName","Normal Day");
					map.put("price",season.getNormalPrice());
				}
				map.put("special",0);
				map.put("season",season.getId());
				map.put("seasonName",season.getSeasonName());
				map.put("reserve",0);
				daysPrices.add(map);
				
			}
		}
		for(Day day : specialDays) {
			if(day!=null && day.getSeason()!=null && day.getSeason().getRoomSeasons()!=null && day.getSeason().getRoomSeasons().getId().equals(idRoom)  ) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("idDay",day.getId());
			map.put("dayName",day.getDayName());
			map.put("startdate",day.getDate_debut());
			map.put("endate",day.getDate_fin());
			map.put("price",day.getPrice());
			map.put("special",day.getSpecial_date());
			map.put("season",day.getSeason().getId());
			map.put("seasonName",day.getSeason().getSeasonName());
			map.put("reserve",0);
			daysPrices.add(map);
			}
		}
		return daysPrices;
	}
	
	@GetMapping("/datesDisabled/{idRoom}/{idSeason}")
	public List<Date> disabledDays(@PathVariable(value="idRoom") String idRoom,@PathVariable(value="idSeason") String idSeason) {
		List<Season> seasons = seasonRepository.getSeasonsbyRoomDisable(0, idRoom,idSeason);
		List<Date> datesBetween=new ArrayList<Date>();
		for(Season season : seasons) {
			datesBetween.addAll(dayService.getDatesBetween(season.getDate_debut(),season.getDate_fin()));			
		}
		return datesBetween;
	}
	
	@DeleteMapping("/specialDay/{idDay}")
	public Boolean archiveDay(@PathVariable(value="idDay") String idDay) {
		return dayService.archiveDay(idDay);
	}
	
	
	@PutMapping("/specialDay/{idDay}")
	public String updateDay(@RequestBody Day day,@PathVariable(value="idDay") String idDay) {
		return dayService.updateDay(day, idDay);
	}
	
}
