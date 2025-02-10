package com.galaxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.DTO.RatingDTO;
import com.galaxy.DTO.ReviewReqDTO;
import com.galaxy.enums.ResponseTitle;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.services.CustomerService;
import com.galaxy.services.RatingService;

@RestController
@RequestMapping("api/review")
public class ReviewController {

	@Autowired
	RatingService ratingSer;
	@Autowired
	CustomerService customerSer;

	@GetMapping("/film/{filmId}")
	public ResponseEntity<RatingDTO> getFilmRatings(@PathVariable int filmId) {
		RatingDTO filmRatings = ratingSer.getFilmRatingsWithAverage(filmId);
		return ResponseEntity.ok(filmRatings);
	}

	@PostMapping("/addNew")
	public ResponseEntity<ApiResponse<?>> addNewReview(@RequestHeader("Authorization") String authHeader,
			@RequestBody ReviewReqDTO review) {
		String token = authHeader.substring(7);
		int CustomerID = (int) customerSer.getProfile(token).get("id");
		System.err.println(review.getFilmID());
		return ratingSer.insertUserReview(CustomerID, review)
				? ResponseEntity.ok(new ApiResponse<>(ResponseTitle.SUCCESS, "Review addedsuccessfully!", 200))
				: ResponseEntity.badRequest().body(new ApiResponse<>(ResponseTitle.ERROR,
						"Review not added!", 400));

	}

}
