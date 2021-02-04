package com.backend.guestnhouse.services;


import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HouseRepository houseRepository;


    public List<Room> getRooms() {
        return roomRepository.findAllRooms(0);
    }


    public House getHouseByRoom(String id){
        Room room =  roomRepository.findById(id).orElse(null);
        if(room != null){
            return  room.getHouse();
        }
        return null;
    }

    public String CreateRoomToHouse(String id, Room room) {
            House house = houseRepository.findById(id).orElse(null);
            if (house != null) {
                room.setHouse(house);
                room.setCreated(new Date());
                roomRepository.save(room);
                return "Room created and House assigned";
            } else return "House or Room not found";
    }

    public String RoomToHouse(String id,String idRoom){
        Room room =  roomRepository.findById(idRoom).orElse(null);
        House house = houseRepository.findById(id).orElse(null);
        if(room != null && house !=null) {
            room.setHouse(house);
            room.setModified(new Date());
            roomRepository.save(room);
            return "House assigned";
        }else return "House or Room not found";
    }


}