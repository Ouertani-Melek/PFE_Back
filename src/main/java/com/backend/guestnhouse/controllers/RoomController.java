package com.backend.guestnhouse.controllers;


import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {


    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/rooms" , method = RequestMethod.GET)
    public ResponseEntity<?> getRooms(){
        return ResponseEntity.ok(roomService.getRooms());
    }
    @RequestMapping(value = "/findRoom/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> findRoom(@PathVariable("id") String id){
        Room room =  roomRepository.findById(id).orElse(null);
        if(room!=null){return ResponseEntity.ok(room);}
        else return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/findHouseRooms/{id}" , method = RequestMethod.GET)
    public List<Room> findHouseRooms(@PathVariable("id") String id){
          return roomRepository.findHouseRooms(id);}

    @RequestMapping(value = "/createRoom",method = RequestMethod.POST)
    public String createRoom(@Valid @RequestBody Room room) {
        room.setCreated(new Date());
        roomRepository.save(room);
        return "Room created";
    }


    @RequestMapping(value = "/updateRoom/{id}", method = RequestMethod.PUT)
    public String updateRoom(@PathVariable("id") String id, @Valid @RequestBody Room room) {
        room.setId(id);
        room.setModified(new Date());
        roomRepository.save(room);
        return "Room edited";
    }

    @RequestMapping(value = "/archiveRoom/{id}", method = RequestMethod.DELETE)
    public String archiveRoom(@PathVariable("id") String id ){
        Room room =  roomRepository.findById(id).orElse(null);
        room.setId(id);
        room.setArchived(1);
        roomRepository.save(room);
        return "room archived";
    }
    @RequestMapping(value = "/roomToHouse/{id}",method = RequestMethod.POST)
    public String CreateAssignRoomToHouse(@PathVariable("id") String id, @Valid @RequestBody Room room)
    {
        return roomService.CreateRoomToHouse(id, room);
    }

    @RequestMapping(value = "/roomToHouse/{id}/{idRoom}",method = RequestMethod.PUT)
    public String AssignHouseToRoom(@PathVariable("id") String id, @PathVariable("idRoom") String idRoom)
    {
        return roomService.RoomToHouse(id, idRoom);
    }
    
    @GetMapping("/{idRoom}")
	public float getpriceRoombyId(@PathVariable(value="idRoom") String idRoom) {
		Room room= roomRepository.findById(idRoom).orElse(null);
		if(room!=null) {
			return room.getPriceRoom();
		}

		return -1;
	}


}
