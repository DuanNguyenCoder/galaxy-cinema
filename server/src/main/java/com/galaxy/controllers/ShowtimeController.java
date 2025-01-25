package com.galaxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.DTO.ShowtimeRequestDTO;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.services.ShowtimeService;

@RestController
@RequestMapping("api/showtime")
public class ShowtimeController {

	@Autowired
	ShowtimeService showTimeSer;

	@GetMapping("/next-week")
	public ResponseEntity<ApiResponse<?>> getMoviesWithShowtimesIn7Days() {
		return showTimeSer.getMoviesWithShowtimesForNext7Days();
	}

	@GetMapping("/film/getAll/{filmID}")
	public ResponseEntity<ApiResponse<?>> getAllShowTimeByFilm(@PathVariable int filmID) {
		return showTimeSer.getAllShowTimeByFilm(filmID);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<?>> createNewShowtime(@RequestBody ShowtimeRequestDTO movieShowtime) {
		return showTimeSer.createNewShowtime(movieShowtime);
	}

	@GetMapping("/film/{filmID}")
	public ResponseEntity<ApiResponse<?>> getShowtimesByFilm(@PathVariable int filmID) {
		return showTimeSer.getShowtimesForNext7Days(filmID);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteShowtime(@PathVariable int id) {
		return showTimeSer.deleteShowtime(id);
	}


}
