package com.backend.guestnhouse.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.guestnhouse.models.Day;
import com.backend.guestnhouse.models.Review;
import com.backend.guestnhouse.models.Room;
import com.backend.guestnhouse.models.Season;
import com.backend.guestnhouse.models.User;
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
	private RoomRepository roomRepository;
	
	public Boolean addReview(Review review ,String idRoom,String idUser) {
		Date now = new Date();
		Room room=roomRepository.findById(idRoom).orElse(null);
		User user=userRepository.findById(idUser).orElse(null);
		if(reviewRepository.existsReview(idRoom,idUser)==null) {
			review.setRoom(room);
			review.setUser(user);
			review.setCreated_at(now);
			review.setValidated(0);
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
			updatereview.setValidated(0);
			reviewRepository.save(updatereview);
			return true;	
		}
			return false;		
		}
	
	public Boolean validateReview(String idReview) {
		Review review=reviewRepository.findById(idReview).orElse(null);
		if(review!=null) {
			review.setValidated(1);
			reviewRepository.save(review);
			return true;
		}
		return false;
	}
	
}
