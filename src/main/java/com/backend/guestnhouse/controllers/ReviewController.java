package com.backend.guestnhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PostMapping("/{idRoom}/{idUser}")
	public String addReview(@RequestBody Review review,@PathVariable(value="idRoom") String idRoom,@PathVariable(value="idUser") String idUser) {
		if(reviewService.addReview(review, idRoom, idUser)) {
			return "success";
		}
		return "error";
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
	
	@PostMapping("/{idReview}")
	public String validateReview(@PathVariable(value="idReview") String idReview) {

		if(reviewService.validateReview(idReview)) {
			return "validated";
		}
		return "not validated";
	}
}
