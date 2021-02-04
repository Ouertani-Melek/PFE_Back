package com.backend.guestnhouse.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.models.Day;
import com.backend.guestnhouse.models.RoomsBooked;
import com.backend.guestnhouse.models.Booking;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.payload.request.ReservationDTO;
import com.backend.guestnhouse.payload.request.ReservationRequest;
import com.backend.guestnhouse.payload.request.RoomDTO;
import com.backend.guestnhouse.repository.DayRepository;
import com.backend.guestnhouse.repository.RoomsbookedRepository;
import com.backend.guestnhouse.repository.ReservationRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.repository.SeasonRepository;
import com.backend.guestnhouse.repository.UserRepository;

@Service
public class RoombookedService {

	
	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private DayService dayService;
	
	@Autowired
	private DayRepository dayRepository;

	@Autowired
	private RoomsbookedRepository prereservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SeasonRepository seasonRepository;
	
    Calendar calendar = Calendar.getInstance();
	Calendar calendarstart = Calendar.getInstance();
    Calendar calendarend = Calendar.getInstance();
	
	public List<RoomDTO> checkroomavailability (ReservationDTO reservationdates,String idHouse) {
		
		calendarstart.setTime(reservationdates.getStartdate());
		calendarstart.add(Calendar.HOUR_OF_DAY, 14);
	    calendarend.setTime(reservationdates.getEnddate());
	    calendarend.add(Calendar.HOUR_OF_DAY, 11);
	    reservationdates.setStartdate(calendarstart.getTime());
	    reservationdates.setEnddate(calendarend.getTime());
		List<RoomDTO> roomsavailables = checkRoomsbydates(reservationdates,idHouse);
		List<RoomDTO> roomstobeRemoved = new ArrayList<RoomDTO>();
			for (int i = 0; i < roomsavailables.size(); i++) {
				if(roomsavailables.get(i)!=null && roomsavailables.get(i).getRoom()!=null &&  roomsavailables.get(i).getRoom().getHouse()!=null) {
					Booking booking = prereservationRepository.checkreservations(reservationdates.getStartdate(), reservationdates.getEnddate(),  roomsavailables.get(i).getRoom().getId());
					if(booking!=null ) {
						roomstobeRemoved.add(roomsavailables.get(i));		
					}
					
				}
				
			}
		roomsavailables.removeAll(roomstobeRemoved);
		return roomsavailables;	
	}
	
	private List<RoomDTO> checkRoomsbydates(ReservationDTO reservationdates,String idHouse){
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();

		if((reservationdates.getStartdate().compareTo(reservationdates.getEnddate()) <= 0)) {
			List<Season> seasons = seasonRepository.checkSeasonReservation(reservationdates.getStartdate(),reservationdates.getEnddate());
			for(Season season : seasons) {
				
				if(season.getRoomSeasons()!=null && season.getRoomSeasons().getHouse()!=null && season.getRoomSeasons().getHouse().getId()!=null && season.getRoomSeasons().getHouse().getId().equals(idHouse)) {
					RoomDTO room = new RoomDTO();
					room.setRoom(season.getRoomSeasons());
					room.setPriceperdayweekend(season.getWeekendPrice());
					room.setPriceperday(season.getNormalPrice());
					room.setTotalpriceroom(priceRoom(season,reservationdates));
					rooms.add(room);
				}
			}
			return rooms;
		}
		return rooms;
		
	}
	
	public float priceRoom(Season season,ReservationDTO reservationdates) {
		float total = 0;
	    Calendar calendarstart = new GregorianCalendar();
	    calendarstart.setTime(reservationdates.getStartdate());
	     
	    Calendar endCalendar = new GregorianCalendar();
	    endCalendar.setTime(reservationdates.getEnddate());
	    while (calendarstart.before(endCalendar)) {
	        Date date = calendarstart.getTime();
	        Day specialday = dayRepository.existsDate(date, season.getId());
	    	calendar.setTime(date);
	    	if(specialday!=null) {
	    		total+=specialday.getPrice();
	    	}else {
	    		int day= calendar.get(Calendar.DAY_OF_WEEK);
		    	if(day==1 || day==7) {
		    		total+=season.getWeekendPrice();
		    	}else {
		    		total+=season.getNormalPrice();
		    	}
	    	}
	        calendarstart.add(Calendar.DATE, 1);
	    }
	    return total;
	}
	
	
	public String addPrereservations(ReservationRequest reservationRequest,String idUser,String idHouse) {
		String response="";
		Calendar calendarprice = Calendar.getInstance();
		User user = userRepository.findById(idUser).orElse(null);
		if(user==null) {
			return "user not found";
		}else {
			for(ReservationDTO reservationdate : reservationRequest.getPrereservations()) {
				Room room = roomRepository.findById(reservationdate.getIdRoom()).orElse(null);
				RoomDTO roomdto = new RoomDTO();
				roomdto.setRoom(room);
				List<RoomDTO> rooms=checkroomavailability(reservationdate, idHouse);
			    List<Date> datesbetween = dayService.getDatesBetween(calendarstart.getTime(), calendarend.getTime());
				if(rooms.contains(roomdto)) {
					for (RoomDTO dtoroom : rooms ) {
						if(dtoroom.getRoom().equals(roomdto.getRoom())) {
							roomdto.setPriceperday(dtoroom.getPriceperday());
							roomdto.setPriceperdayweekend(dtoroom.getPriceperdayweekend());
							roomdto.setTotalpriceroom(dtoroom.getTotalpriceroom());
						}	
					}
					RoomsBooked prereservation = new RoomsBooked();
					RoomsBooked existsPrereservation = prereservationRepository.existsDatesroom(calendarstart.getTime(),calendarend.getTime(),reservationdate.getIdRoom(),idUser);
					if(room!=null && existsPrereservation==null) {
						prereservation.setIdUser(idUser);
						prereservation.setIdHouse(idHouse);
						for(Date date : datesbetween) {
							calendarprice.setTime(date);
							int dayofweek = calendarprice.get(Calendar.DAY_OF_WEEK);
							if(dayofweek==1 || dayofweek==7) {
								prereservation.setPriceperdayweekend(roomdto.getPriceperdayweekend());
							}else {
								prereservation.setPriceperday(roomdto.getPriceperday());
							}
						}
						prereservation.setRoom(room);
						prereservation.setPrice(roomdto.getTotalpriceroom());
						prereservation.setReserved(0);
						prereservation.setStartdate(calendarstart.getTime());
						prereservation.setEnddate(calendarend.getTime());
						prereservation.setCreated(new Date());
						prereservationRepository.save(prereservation);
						response="Prereservation added";
					}else {
						response="already added";
					}
				}else {
					response="room unavailable";
				}			
			}
		}
		
		
		return response;
	}
	
	
	public List<RoomsBooked> getPrereservationsbyHouse(String idHouse,ReservationDTO reservationdates){
		calendarstart.setTime(reservationdates.getStartdate());
		calendarstart.add(Calendar.HOUR_OF_DAY, 14);
	    calendarend.setTime(reservationdates.getEnddate());
	    calendarend.add(Calendar.HOUR_OF_DAY, 11);
		return prereservationRepository.getPrereservationbyHouse(calendarstart.getTime(), calendarend.getTime(), idHouse);
	}
	
	public Boolean deletePrereservation(String idprereservation) {
		RoomsBooked prereservation = prereservationRepository.findById(idprereservation).orElse(null);
		if(prereservation!=null) {
			prereservation.setArchived(1);
			prereservationRepository.save(prereservation);
			return true;
		}
		return false;
	}
	
	public Boolean approvedPrereservation(String idprereservation) {
		RoomsBooked prereservation = prereservationRepository.findById(idprereservation).orElse(null);
		if(prereservation!=null) {
			prereservation.setApproved(1);
			prereservation.setModified(new Date());
			prereservationRepository.save(prereservation);
			return true;
		}
		return false;
	}
}
