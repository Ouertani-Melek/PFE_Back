package com.backend.guestnhouse.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.models.RoomsBooked;
import com.backend.guestnhouse.models.Booking;
import com.backend.guestnhouse.models.Payment;
import com.backend.guestnhouse.models.Promocode;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.payload.request.ReservationDTO;
import com.backend.guestnhouse.repository.RoomsbookedRepository;
import com.backend.guestnhouse.repository.PaymentRepository;
import com.backend.guestnhouse.repository.PromocodeRepository;
import com.backend.guestnhouse.repository.ReservationRepository;
import com.backend.guestnhouse.repository.UserRepository;

@Service
public class ReservationService {
	
	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoomsbookedRepository prereservationRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PromocodeRepository promocodeRepository;
	
	@Autowired
	private RoomsbookedRepository roombookedRepository;
	
	Calendar calendarstart = Calendar.getInstance();
    Calendar calendarend = Calendar.getInstance();

	public String addReservation(ReservationDTO reservationdto,String idUser,String idHouse,String code) {
		User user = userRepository.findById(idUser).orElse(null);
		calendarstart.setTime(reservationdto.getStartdate());
		calendarstart.add(Calendar.HOUR_OF_DAY, 14);
	    calendarend.setTime(reservationdto.getEnddate());
	    calendarend.add(Calendar.HOUR_OF_DAY, 11);
	    reservationdto.setStartdate(calendarstart.getTime());
	    reservationdto.setEnddate(calendarend.getTime());
	    float totalprice=0;
	    Promocode promocode = promocodeRepository.findByCode(code).orElse(null);
		List<RoomsBooked> roomsBookeds=prereservationRepository.getPrereservationbyIduser(reservationdto.getStartdate(), reservationdto.getEnddate(), idUser,idHouse);
		if(user!=null && roomsBookeds.size()>0) {
			Booking booking = new Booking();
			Payment payment = new Payment();
			payment.setPaymentDate(new Date());
			booking.setStartdate(reservationdto.getStartdate());
			booking.setEnddate(reservationdto.getEnddate());
			booking.setUser(user);
			booking.setCreated(new Date());
			booking.setRoomsbookedcompleted(roomsBookeds);
			for(RoomsBooked prereservation : roomsBookeds) {
				prereservation.setReserved(1);
				totalprice+=prereservation.getPrice();
				prereservationRepository.save(prereservation);
				cancelusersRoombooked(reservationdto.getStartdate(), 
						reservationdto.getEnddate(), prereservation.getRoom().getId(), 
						prereservation.getIdHouse(), prereservation.getIdUser());
			}
			if(promocode!=null) {
		    	totalprice-=(totalprice*promocode.getDiscount())/100;
		    }
			booking.setPrice(totalprice);
			reservationRepository.save(booking);
			payment.setBooking(booking);
			payment.setTotalprice(totalprice);
			paymentRepository.save(payment);
			return "success";
		}else if(user==null) {
			return "user not found";
		}else {
			return "room unvailable";
		}
		
	}
	
	
	private Boolean cancelusersRoombooked(Date startdate,Date enddate,String idRoom,String idhouse,String idUser) {
		Query query = new Query();
		query.addCriteria(Criteria.where("startdate").lte(startdate).lte(enddate));
		query.addCriteria(Criteria.where("enddate").gte(startdate).gte(enddate));
		query.addCriteria(Criteria.where("room.id").is(idRoom));
		query.addCriteria(Criteria.where("idHouse").is(idhouse));
		query.addCriteria(Criteria.where("idUser").ne(idUser));
		query.addCriteria(Criteria.where("archived").is(0));
		query.addCriteria(Criteria.where("reserved").is(0));
		Update update = new Update();
		update.set("archived",1);
		mongoOperations.updateMulti(query, update, RoomsBooked.class);
		return true;
	}
	
	public Boolean cancelReservation(String idReservation) {
		Booking booking=reservationRepository.findById(idReservation).orElse(null);
		if(booking!=null) {
			booking.setArchived(1);
			reservationRepository.save(booking);
			return true;
		}
		return false;
	}
	

	
}
