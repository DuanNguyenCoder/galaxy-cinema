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

        @Autowired
        OrderFilmRepo orderFilmRepo;

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

        public ResponseEntity<ApiResponse<?>> getShowtimesForNext7Days(int filmID) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime endDate = now.plusDays(6).withHour(23).withMinute(59).withSecond(59);
                List<Showtime> showtimes = showtimeRepo.findByStartShowBetweenAndFilmIdOrderByStartShowAsc(now, endDate,
                                filmID);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                List<ShowtimeDTO> showtimeDTOs = showtimes.stream()
                                .map(showtime -> {
                                        ShowtimeDTO dto = new ShowtimeDTO();
                                        dto.setId(showtime.getId());
                                        dto.setDate(showtime.getStartShow().format(dateFormatter));
                                        dto.setStartShow(showtime.getStartShow().format(timeFormatter));
                                        dto.setScreen(showtime.getScreen());
                                        return dto;
                                }).collect(Collectors.toList());
                return ResponseEntity.ok().body(new ApiResponse<>(ResponseTitle.SUCCESS,
                                "Showtime retrieved successfully", 200, showtimeDTOs));
        }

        public ResponseEntity<ApiResponse<?>> getMoviesWithShowtimesForNext7Days() {
                // Lấy thời gian hiện tại và 6 ngày sau
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime endDate = now.plusDays(6).withHour(23).withMinute(59).withSecond(59);

                List<Showtime> showtimes = showtimeRepo.findShowtimesInNext7Days(now, endDate);

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                Map<Film, List<Showtime>> showtimesByFilm = showtimes.stream()
                                .collect(Collectors.groupingBy(Showtime::getFilm));

                List<MovieShowtimeDTO> result = new ArrayList<>();

                showtimesByFilm.forEach((film, filmShowtimes) -> {
                        MovieShowtimeDTO movieDto = new MovieShowtimeDTO();
                        movieDto.setFilmID(film.getId());
                        movieDto.setName(film.getName());
                        movieDto.setImage(film.getImage());

                        List<ShowtimeDTO> showtimeDTOs = filmShowtimes.stream()
                                        .map(showtime -> {
                                                ShowtimeDTO dto = new ShowtimeDTO();
                                                dto.setId(showtime.getId());
                                                dto.setDate(showtime.getStartShow().format(dateFormatter));
                                                dto.setStartShow(showtime.getStartShow().format(timeFormatter));
                                                dto.setScreen(showtime.getScreen());
                                                return dto;
                                        })
                                        .collect(Collectors.toList());

                        movieDto.setShowTimes(showtimeDTOs);
                        result.add(movieDto);
                });

                return ResponseEntity.ok()
                                .body(new ApiResponse<>(ResponseTitle.SUCCESS, "Retrieved successfully", 200, result));
        }

        public ResponseEntity<ApiResponse<?>> getSeatOrdered(int showtimeID) {
                List<OrderFilm> orderFilms = orderFilmRepo.findByMovieIdAndInvoiceStatus(showtimeID, "PAID");
                List<String> seat = new ArrayList<>();
                for (OrderFilm orderFilm : orderFilms) {
                        seat.add(orderFilm.getSeat());
                }
                return ResponseEntity.ok().body(new ApiResponse<>(ResponseTitle.SUCCESS, "Retrieved successfully", 200,
                                seat));
        }
}
