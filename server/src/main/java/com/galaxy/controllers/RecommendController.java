package com.galaxy.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.galaxy.DTO.AlgorithmDataResDTO;
import com.galaxy.repositories.RatingRepo;

import com.galaxy.services.RatingService;

@RestController
@RequestMapping("api/recommend")
public class RecommendController {

	@Autowired
	RatingService ratingSerImp;

	@Autowired
	RatingRepo ratingRepo;

	@GetMapping("a/{id}")
	public ResponseEntity<AlgorithmDataResDTO> Matrix(@PathVariable int id) {
		AlgorithmDataResDTO dataRes = ratingSerImp.rec(id);
		dataRes.formatSimilarMatrix();

		return ResponseEntity.ok(dataRes);
	}

}
