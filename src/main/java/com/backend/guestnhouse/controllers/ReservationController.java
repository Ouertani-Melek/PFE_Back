package com.backend.guestnhouse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.models.Booking;
import com.backend.guestnhouse.payload.request.ReservationDTO;
import com.backend.guestnhouse.services.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	
	@PostMapping("/{idUser}/{idHouse}/{code}")
	public String addReservation(@RequestBody ReservationDTO reservationdto,@PathVariable(value="idUser") String idUser,@PathVariable(value="idHouse") String idHouse,@PathVariable(value="code") String code) {
		return reservationService.addReservation(reservationdto,idUser,idHouse,code);
	}
	
	@DeleteMapping("/{idReservation}")
	public Boolean archiveDay(@PathVariable(value="idReservation") String idReservation) {
		return reservationService.cancelReservation(idReservation);
	}
	
}
