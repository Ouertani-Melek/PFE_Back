package com.backend.guestnhouse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.guestnhouse.models.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

	@Query("{'archived' : 0, 'room.id' : ?0,'user.id':?1}")
	public Review existsReview(String idRoom,String idUser);
}
