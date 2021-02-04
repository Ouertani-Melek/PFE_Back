package com.backend.guestnhouse.controllers;


import com.backend.guestnhouse.models.Amenities;
import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.repository.AmenitiesRepository;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.services.AmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenitiesController {

    @Autowired
    AmenitiesRepository amenitiesRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    AmenitiesService amenitiesService;

    @RequestMapping(value = "/allAmenities" , method = RequestMethod.GET)
    public ResponseEntity<?> getAmenities(){
        return ResponseEntity.ok(amenitiesService.getAmenities());
    }

    @RequestMapping(value = "/AmenitiesByType/{type}" , method = RequestMethod.GET)
    public ResponseEntity<?> getAmenitiesByType(@PathVariable("type") String type){
        return ResponseEntity.ok(amenitiesRepository.findAmenitiesByType(type));
    }

    @RequestMapping(value = "/createAmenities",method = RequestMethod.POST)
    public void createAmenities(@Valid @RequestBody Amenities amenities)
    {   amenities.setCreated(new Date());
        amenitiesRepository.save(amenities); }

    @RequestMapping(value = "/updateAmenities/{id}", method = RequestMethod.PUT)
    public void updateAmenities(@PathVariable("id") String id, @Valid @RequestBody Amenities amenities) {
        amenities.setId(id);
        amenities.setModified(new Date());
        amenitiesRepository.save(amenities);
    }

    @RequestMapping(value = "/archiveAmenities/{id}", method = RequestMethod.DELETE)
    public String archiveRoom(@PathVariable("id") String id ){
        Amenities amenities =  amenitiesRepository.findById(id).orElse(null);
        amenities.setId(id);
        amenities.setArchived(1);
        amenitiesRepository.save(amenities);
        return "Amenities archived";
    }

    @RequestMapping(value = "/amenitiesToRoom/{id}",method = RequestMethod.PUT)
    public String CreateAssignAmenitiesToRoom(@PathVariable("id") String id, @Valid @RequestBody Amenities amenities)
    {
       return amenitiesService.createAmenitesToRoom(id,amenities);
    }

    @RequestMapping(value = "/amenitiesToRoom/{id}/{idAmenities}",method = RequestMethod.PUT)
    public String AssignAmenitesToRoom(@PathVariable("id") String id, @PathVariable("idAmenities") String idAmenities)
    {
        return amenitiesService.amenitesToRoom(id, idAmenities);
    }

    @RequestMapping(value = "/amenitiesToHouse/{id}",method = RequestMethod.PUT)
    public String CreateAssignAmenitiesToHouse(@PathVariable("id") String id, @Valid @RequestBody Amenities amenities)
    {
       return amenitiesService.createAmenitesToHouse(id, amenities);
    }

    @RequestMapping(value = "/amenitiesToHouse/{id}/{idAmenities}",method = RequestMethod.PUT)
    public String AssignAmenitesToHouse(@PathVariable("id") String id, @PathVariable("idAmenities") String idAmenities)
    {
       return amenitiesService.amenitesToHouse(id, idAmenities);
    }

}

