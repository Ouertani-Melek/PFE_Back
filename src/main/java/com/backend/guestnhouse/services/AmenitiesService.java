package com.backend.guestnhouse.services;

import com.backend.guestnhouse.models.Amenities;
import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.repository.AmenitiesRepository;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class AmenitiesService {

    @Autowired
    AmenitiesRepository amenitiesRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HouseRepository houseRepository;


    public List<Amenities> getAmenities() {
        return amenitiesRepository.findAllAmenities(0);
    }


    public String createAmenitesToHouse(String id, Amenities amenities){
        House house = houseRepository.findById(id).orElse(null);
        if(house!=null) {
            if (house.getAmenities() == null) {
                house.setAmenities(new HashSet<>());
                house.getAmenities().add(amenities);
            } else house.getAmenities().add(amenities);
            amenities.setCreated(new Date());
            amenitiesRepository.save(amenities);
            house.setModified(new Date());
            houseRepository.save(house);
            return "amenites created and assigned to house";
        } else return "house not found";

    }
    public String createAmenitesToRoom(String id, Amenities amenities){
        Room room =  roomRepository.findById(id).orElse(null);
        if(room!=null) {
            if (room.getAmenities() == null) {
                room.setAmenities(new HashSet<>());
                room.getAmenities().add(amenities);
            } else room.getAmenities().add(amenities);
            amenities.setCreated(new Date());
            amenitiesRepository.save(amenities);
            room.setModified(new Date());
            roomRepository.save(room);
            return "amenities created and assigned to room";
        }else return "room not found";
    }


    public String amenitesToHouse(String id,  String idAmenities){
        House house = houseRepository.findById(id).orElse(null);
        Amenities amenities = amenitiesRepository.findById(idAmenities).orElse(null);
        if (house != null && amenities != null) {
            if (house.getAmenities() == null) {
                house.setAmenities(new HashSet<>());
                house.getAmenities().add(amenities);
            } else house.getAmenities().add(amenities);
            house.setModified(new Date());
            houseRepository.save(house);
            return "amenities Assigned to house";
        }else return "amenities or house are null";
    }
    public String amenitesToRoom( String id,  String idAmenities){
        Room room = roomRepository.findById(id).orElse(null);
        Amenities amenities = amenitiesRepository.findById(idAmenities).orElse(null);
        if(room!=null && amenities!=null){
           if(room.getAmenities()==null){
              room.setAmenities(new HashSet<>());
              room.getAmenities().add(amenities);
           } else room.getAmenities().add(amenities);
           room.setModified(new Date());
          roomRepository.save(room);
          return "amenities assinged to room";
        } else return "room or amenities are null";
    }

}
