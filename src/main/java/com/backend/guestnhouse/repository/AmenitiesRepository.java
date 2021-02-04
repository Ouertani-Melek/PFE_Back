package com.backend.guestnhouse.repository;

import com.backend.guestnhouse.models.Amenities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenitiesRepository extends MongoRepository<Amenities, String> {

    @Query("{archived : ?0}")
    List<Amenities> findAllAmenities(int archived);

    @Query("{type : ?0}")
    List<Amenities> findAmenitiesByType(String type);
}
