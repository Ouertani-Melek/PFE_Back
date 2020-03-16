package com.backend.guestnhouse.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.guestnhouse.models.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String>  {

}
