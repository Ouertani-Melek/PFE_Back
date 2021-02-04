package com.backend.guestnhouse.controllers;


import com.backend.guestnhouse.models.Activities;
import com.backend.guestnhouse.repository.ActivitiesRepository;
import com.backend.guestnhouse.services.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {

    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    ActivitiesService activitiesService;


    @RequestMapping(value = "/allActivities" , method = RequestMethod.GET)
    public ResponseEntity<?> getActivities(){
        return ResponseEntity.ok(activitiesService.getActivities());
    }

    @RequestMapping(value = "/createActivity",method = RequestMethod.POST)
    public void createActivities(@Valid @RequestBody Activities activities)
    {   activities.setCreated(new Date());
        activitiesRepository.save(activities); }

    @RequestMapping(value = "/updateActivity/{id}", method = RequestMethod.PUT)
    public void updateActivity(@PathVariable("id") String id, @Valid @RequestBody Activities activities) {
        activities.setId(id);
        activities.setModified(new Date());
        activitiesRepository.save(activities);
    }

    @RequestMapping(value = "/archiveActivity/{id}", method = RequestMethod.DELETE)
    public String archiveRoom(@PathVariable("id") String id ){
        Activities activities =  activitiesRepository.findById(id).orElse(null);
        activities.setId(id);
        activities.setArchived(1);
        activitiesRepository.save(activities);
        return "Activity archived";
    }

    @RequestMapping(value = "/ActivityToHouse/{id}/{idActivity}",method = RequestMethod.PUT)
    public String AssignActivityToHouse(@PathVariable("id") String id, @PathVariable("idActivity") String idActivity)
    {
        return activitiesService.activityToToHouse(id, idActivity);
    }
}
