package com.backend.guestnhouse.repository;

import com.backend.guestnhouse.models.Activities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivitiesRepository  extends MongoRepository<Activities, String> {
    @Query("{archived : ?0}")
    List<Activities> findAllActivites(int archived);
}
