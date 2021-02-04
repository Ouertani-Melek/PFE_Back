package com.backend.guestnhouse.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.models.Day;
import com.backend.guestnhouse.models.House;
import com.backend.guestnhouse.models.Review;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.models.User;
import com.backend.guestnhouse.repository.HouseRepository;
import com.backend.guestnhouse.repository.ReviewRepository;
import com.backend.guestnhouse.repository.RoomRepository;
import com.backend.guestnhouse.repository.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HouseRepository houseRepository;
	
	public Boolean addReview(Review review ,String idHouse,String idUser) {
		Date now = new Date();
		House house=houseRepository.findById(idHouse).orElse(null);
		User user=userRepository.findById(idUser).orElse(null);
		if(reviewRepository.existsReview(idHouse,idUser)==null) {
			review.setHouse(house);
			review.setUser(user);
			review.setCreated_at(now);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}
	
	public Boolean archiveReview(String idReview) {
		Review review=reviewRepository.findById(idReview).orElse(null);
		if(review!=null) {
			review.setArchived(1);
			reviewRepository.save(review);
			return true;
		}
		return false;
			
	}
	
	public Boolean updateReview(Review review,String idReview) {
		Date now = new Date();
		Review updatereview=reviewRepository.findById(idReview).orElse(null);
		if(updatereview!=null) {
			updatereview.setComment(review.getComment());
			updatereview.setRating(review.getRating());
			updatereview.setModified_at(now);
			reviewRepository.save(updatereview);
			return true;	
		}
			return false;		
		}
	
	public float ratingbyHouse(String idHouse) {
				List<Review> reviews = reviewRepository.findAllReviews(0, idHouse);
				float rating=0;
				float average=0;
				if(reviews.size()==0) {
					return 0;
				}else {
					for(Review review : reviews) {
						rating+=review.getRating();
						}
						average=rating/reviews.size();
						return average;	
				}
				
			}
			

	
}
