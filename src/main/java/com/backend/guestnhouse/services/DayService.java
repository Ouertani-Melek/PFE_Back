package com.backend.guestnhouse.services;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.models.Day;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.repository.DayRepository;
import com.backend.guestnhouse.repository.SeasonRepository;

@Service
public class DayService {
	
	@Autowired
	private DayRepository dayRepository;
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	
	public Boolean addSpecialDay(Day day ,String idSeason) {
		Season season=seasonRepository.findById(idSeason).orElse(null);
		if(dayRepository.existsDate(day.getDate_debut(), idSeason)==null) {
			day.setSeason(season);
			day.setSpecial_date(1);
			dayRepository.save(day);
			return true;
		}
		return false;
	}
	
	
	
	public Boolean archiveDay(String idDay) {
		Day day=dayRepository.findById(idDay).orElse(null);
		if(day!=null) {
			day.setArchived(1);
			dayRepository.save(day);
			return true;
		}
		return false;
			
	}
	
	public String updateDay(Day day,String idDay) {
		Day updateday=dayRepository.findById(idDay).orElse(null);
		if(updateday!=null) {
			updateday.setPrice(day.getPrice());
			dayRepository.save(updateday);
			return "special_day updated";	
		}
			return "not updated";		
		}
	
	public List<Date> getDatesBetween(Date startDate, Date endDate) {
			    List<Date> datesInRange = new ArrayList<>();
			    Calendar calendar = new GregorianCalendar();
			    calendar.setTime(startDate);
			     
			    Calendar endCalendar = new GregorianCalendar();
			    endCalendar.setTime(endDate);
			 
			    while (calendar.before(endCalendar)) {
			        Date result = calendar.getTime();
			        datesInRange.add(result);
			        calendar.add(Calendar.DATE, 1);
			    }
			    return datesInRange;
			}
}
	
