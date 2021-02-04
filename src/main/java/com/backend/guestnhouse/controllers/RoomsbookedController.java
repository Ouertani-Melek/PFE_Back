package com.backend.guestnhouse.controllers;

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

import com.backend.guestnhouse.models.RoomsBooked;
import com.backend.guestnhouse.payload.request.ReservationDTO;
import com.backend.guestnhouse.payload.request.ReservationRequest;
import com.backend.guestnhouse.payload.request.RoomDTO;
import com.backend.guestnhouse.services.RoombookedService;

@RestController
@RequestMapping("/roomsbooked")
public class RoomsbookedController {

	@Autowired
	private RoombookedService roombookedService;
	
	@PostMapping("/{idUser}/{idHouse}")
	public String addRoombooked(@RequestBody ReservationRequest reservationRequest,@PathVariable(value="idUser") String idUser,@PathVariable(value="idHouse") String idHouse) {
		return roombookedService.addPrereservations(reservationRequest,idUser,idHouse);
	}
	
	@DeleteMapping("/{idroombooked}")
	public String deleteRoombooked(@PathVariable(value="idroombooked") String idroombooked) {
		if(roombookedService.deletePrereservation(idroombooked)) {
			return "archived";
		}
		return "error";
	}
	
	@PutMapping("/approve/{idroombooked}")
	public String approveRoombooked(@PathVariable(value="idroombooked") String idroombooked) {
		if(roombookedService.approvedPrereservation(idroombooked)) {
			return "approved";
		}
		return "error";
	}
	
	@GetMapping("/{idHouse}")
	public List<RoomsBooked> getPrereservationsbyhouse(@PathVariable(value="idHouse") String idHouse,@RequestBody ReservationDTO reservationdates){
		return roombookedService.getPrereservationsbyHouse(idHouse, reservationdates);
	}
	
	@GetMapping("/getroomsAvailable/{idHouse}")
	public List<RoomDTO> roomsAvailable(@PathVariable(value="idHouse") String idHouse,@RequestBody ReservationDTO reservationdates){
		return roombookedService.checkroomavailability(reservationdates,idHouse);
	}
	
}
