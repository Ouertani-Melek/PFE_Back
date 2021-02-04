package com.backend.guestnhouse.repository;

import com.backend.guestnhouse.models.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {
    Boolean existsByhouseName(String houseName);

    @Query("{archived : ?0}")
    List<House> findAllHouses(int archived);

    @Query("{user : ?0}")
    List<House> findHouseByUser(String id);

    @Query("{user : ?0}")
    House findHouseByUserId(String id);

}
