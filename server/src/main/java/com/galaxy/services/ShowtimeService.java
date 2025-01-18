package com.galaxy.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.galaxy.DTO.MovieShowtimeDTO;
import com.galaxy.DTO.ShowtimeDTO;
import com.galaxy.DTO.ShowtimeRequestDTO;
import com.galaxy.entities.Film;
import com.galaxy.entities.OrderFilm;
import com.galaxy.entities.Showtime;
import com.galaxy.enums.ResponseTitle;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.repositories.FilmRepo;
import com.galaxy.repositories.OrderFilmRepo;
import com.galaxy.repositories.ShowtimeRepo;

@Service
public class ShowtimeService {

        @Autowired
        ShowtimeRepo showtimeRepo;

        @Autowired
        FilmRepo filmRepo;


        public ResponseEntity<ApiResponse<?>> getAllShowTimeByFilm(int filmID) {
                List<Showtime> showtimes = showtimeRepo.findByFilmIdOrderByStartShowAsc(filmID);
                return ResponseEntity.ok()
                                .body(new ApiResponse<>(ResponseTitle.SUCCESS, "Showtime created successfully!", 201,
                                                showtimes));
        }

        public ResponseEntity<ApiResponse<?>> createNewShowtime(ShowtimeRequestDTO showtime) {
                Film film = filmRepo.findById(showtime.getFilmID())
                                .orElseThrow(() -> new RuntimeException("Film not found"));
                Showtime newShowtime = new Showtime();
                newShowtime.setStartShow(showtime.getStartShow());
                newShowtime.setScreen(showtime.getScreen());
                newShowtime.setFilm(film);
                Showtime savedShowtime = showtimeRepo.save(newShowtime);
                return savedShowtime != null
                                ? ResponseEntity.ok()
                                                .body(new ApiResponse<>(ResponseTitle.SUCCESS,
                                                                "Showtime created successfully!", 201))
                                : ResponseEntity.ok().body(new ApiResponse<>(ResponseTitle.ERROR, "Error", 400));
        }

        public ResponseEntity<ApiResponse<?>> deleteShowtime(int id) {
                try {
                        Showtime showtime = showtimeRepo.findById(id)
                                        .orElseThrow(() -> new RuntimeException("Showtime not found with id: " + id));

                        showtimeRepo.delete(showtime);

                        return ResponseEntity.ok()
                                        .body(new ApiResponse<>(ResponseTitle.SUCCESS, "Showtime deleted successfully!",
                                                        200));
                } catch (Exception e) {
                        return ResponseEntity.badRequest()
                                        .body(new ApiResponse<>(ResponseTitle.ERROR,
                                                        "Failed to delete showtime: " + e.getMessage(), 400));
                }
        }


}
