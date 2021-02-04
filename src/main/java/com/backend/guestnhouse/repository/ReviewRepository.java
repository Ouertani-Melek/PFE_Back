package com.backend.guestnhouse.repository;

import java.util.List;

import com.backend.guestnhouse.models.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.guestnhouse.models.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

	@Query("{'archived' : 0, 'house.id' : ?0,'user.id':?1}")
	public Review existsReview(String idHouse,String idUser);
	
	@Query("{'archived' : ?0,'house.id' : ?1}")
	public List<Review> findAllReviews(int archived,String idHouse);

	@Query("{'user.id' : ?0}")
	List<Review> findReviewByUser(String idUser);
	
}
