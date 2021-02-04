package com.backend.guestnhouse.controllers;


import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.User;

import com.backend.guestnhouse.models.es.EsHouse;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.repository.UserRepository;
import com.backend.guestnhouse.repository.es.EsHouseRepository;
import com.backend.guestnhouse.services.HouseService;
import com.backend.guestnhouse.services.es.EsHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HouseService houseService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EsHouseRepository esHouseRepository;

    @Autowired
    EsHouseService esHouseService;

    @RequestMapping(value = "/helloTest" , method = RequestMethod.GET)
    public String testGreetings() {
        return "Hello";
    }

    @RequestMapping(value = "/houses" , method = RequestMethod.GET)
    public ResponseEntity<?> getHouses(){
        return ok(houseService.getHouses());
    }


    @RequestMapping(value = "/findHouse/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> findHouse(@PathVariable("id") String id){
        House house = houseRepository.findById(id).orElse(null);
        if(house!=null){return ok(house);}
        else return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/findHouseByUser/{id}" , method = RequestMethod.GET)
    public ResponseEntity findHouseByUser(@PathVariable("id") String id){
        House house = houseRepository.findHouseByUserId(id);
        Map<Object, Object> model = new HashMap<>();
        model.put("houseName", house.getHouseName());
        model.put("_id", house.getId());
        model.put("description", house.getDescription());
        model.put("houseType", house.getHouseType());
        model.put("priceHouse", house.getPriceHouse());
        model.put("addressLine", house.getAddressLine());
        model.put("created", house.getCreated());
        model.put("images", house.getImages());
        model.put("archived", house.getArchived());
        return ok(model);}

    @RequestMapping(value = "/findHouseByRoom/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> findHouseByRoom(@PathVariable("id") String id){
        Room room =  roomRepository.findById(id).orElse(null);
        if(room!=null){return ok(room.getHouse());}
        else return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/findUser/{id}" , method = RequestMethod.GET)
    public ResponseEntity<?> findUserByHouse(@PathVariable("id") String id){
        House house = houseRepository.findById(id).orElse(null);
        if(house!=null){return ok(house.getUser());}
        else return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/Users" , method = RequestMethod.GET)
    public List<User> getAllUserss() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/createHouse",method = RequestMethod.POST)
    public String createHouse(@Valid @RequestBody House house)
    {   house.setCreated(new Date());
        return houseService.addHouse(house); }

    @RequestMapping(value = "/updateHouse/{id}", method = RequestMethod.PUT)
    public void updateHouse(@PathVariable("id") String id,@Valid @RequestBody House house) {
        house.setId(id);
        house.setModified(new Date());
        houseRepository.save(house);
    }


    @RequestMapping(value = "/houseToUser/{id}",method = RequestMethod.POST)
    public ResponseEntity CreateAssignHouseToUser(@PathVariable("id") String id, @Valid @RequestBody House house)
    {
       return houseService.CreateHouseToUser(id,house);
    }

    @RequestMapping(value = "/houseToUser/{id}/{idHouse}",method = RequestMethod.PUT)
    public String AssignHouseToUser(@PathVariable("id") String id, @PathVariable("idHouse") String idHouse)
    {
        return houseService.HouseToUser(id,idHouse);
    }
    
    
}
