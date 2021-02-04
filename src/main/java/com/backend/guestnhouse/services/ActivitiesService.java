package com.backend.guestnhouse.services;


import com.backend.guestnhouse.models.Activities;
import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.repository.ActivitiesRepository;
import com.backend.guestnhouse.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ActivitiesService {


    @Autowired
    HouseRepository houseRepository;

    @Autowired
    ActivitiesRepository activitiesRepository;


    public List<Activities> getActivities() {
        return activitiesRepository.findAllActivites(0);
    }

    public String activityToToHouse(String id, String idActivity) {
        House house = houseRepository.findById(id).orElse(null);
        Activities activities = activitiesRepository.findById(idActivity).orElse(null);
        if (house != null && activities != null) {
            if (house.getActivities() == null) {
                house.setActivities(new HashSet<>());
                house.getActivities().add(activities);
            } else house.getActivities().add(activities);
            houseRepository.save(house);
            return "Activity Assigned to house";
        } else return "Activity or house are null";
    }
}
