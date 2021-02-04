package com.backend.guestnhouse.services;

import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.repository.UserRepository;
import com.backend.guestnhouse.repository.es.EsHouseRepository;
import com.backend.guestnhouse.services.es.EsHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HouseService {


    @Autowired
    HouseRepository houseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EsHouseService esHouseService;



    public List<House> getHouses() {
        return houseRepository.findAllHouses(0);
    }

    public String addHouse(House house) {
        if (!houseRepository.existsByhouseName(house.getHouseName())) {
            houseRepository.save(house);
            esHouseService.houseToEs(house);
            return "Maison cree";
        } else return "Maison exite deja";
    }

    public ResponseEntity CreateHouseToUser(String id, House house) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (!user.isOwner()) {
                if (!houseRepository.existsByhouseName(house.getHouseName())) {
                    if (house.getUser() == null) {
                        house.setUser(user);
                        user.setOwner(true);
                        userRepository.save(user);
                        house.setCreated(new Date());
                        houseRepository.save(house);
                        esHouseService.houseToEs(house);
                        return ResponseEntity.ok(house);
                    } else return new ResponseEntity(HttpStatus.FORBIDDEN);
                } else return new ResponseEntity(HttpStatus.FOUND);
            } else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public String HouseToUser(String id, String idHouse) {
        User user = userRepository.findById(id).orElse(null);
        House house = houseRepository.findById(idHouse).orElse(null);
        if (user != null && house != null) {
            if (!user.isOwner()) {
                if (house.getUser() == null) {
                    house.setUser(user);
                    house.setModified(new Date());
                    houseRepository.save(house);
                    return "House added to user";
                }
            } else return "User already has a house";
        }
        return "House or User not found";
    }

    public String archiveHouse(String id) {
        House house = houseRepository.findById(id).orElse(null);
        if (house != null) {
            List<Room> lr = roomRepository.findHouseRooms(id);
            if (!lr.isEmpty()) {
                for (Room r : lr) {
                    r.setArchived(1);
                    roomRepository.save(r);
                }
            }
            house.setArchived(1);
            houseRepository.save(house);
            return "House Archived";
        } else return "House not found";
    }



}
