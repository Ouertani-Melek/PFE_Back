package com.backend.guestnhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.guestnhouse.models.Day;
import com.backend.guestnhouse.models.Review;
import com.backend.guestnhouse.repository.ReviewRepository;
import com.backend.guestnhouse.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/{idHouse}/{idUser}")
	public String addReview(@RequestBody Review review,@PathVariable(value="idHouse") String idHouse,@PathVariable(value="idUser") String idUser) {
		if(reviewService.addReview(review, idHouse, idUser)) {
			return "success";
		}
		return "error";
	}
	
	@GetMapping("/{idHouse}")
	public List<Review> listReviews(@PathVariable(value="idHouse") String idHouse){
		return reviewRepository.findAllReviews(0, idHouse);
	}
	
	@GetMapping("/review/{idReview}")
	public Review getReviewById(@PathVariable(value="idReview") String idReview){
		return reviewRepository.findById(idReview).orElse(null);
	}
	
	@DeleteMapping("/{idReview}")
	public Boolean archiveReview(@PathVariable(value="idReview") String idReview) {
		return reviewService.archiveReview(idReview);
	}
	
	@PutMapping("/{idReview}")
	public String updateReview(@RequestBody Review review,@PathVariable(value="idReview") String idReview) {

		if(reviewService.updateReview(review, idReview)) {
			return "updated";
		}
		return "not updated";
	}
	
	@GetMapping("/rating/{idHouse}")
	public float ratingbyHouse(@PathVariable(value="idHouse") String idHouse) {
		return reviewService.ratingbyHouse(idHouse);
	}

	@GetMapping("users/{idUser}")
	public List<Review> listReviewsByUser(@PathVariable(value="idUser") String idUser){
		return reviewRepository.findReviewByUser(idUser);
	}
	
}
