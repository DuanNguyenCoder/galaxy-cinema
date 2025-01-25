package com.galaxy.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.galaxy.entities.Showtime;

public interface ShowtimeRepo extends JpaRepository<Showtime, Integer> {

        List<Showtime> findByFilmIdOrderByStartShowAsc(int filmId);

        List<Showtime> findByStartShowBetweenAndFilmIdOrderByStartShowAsc(LocalDateTime StartShow,
                        LocalDateTime endShow,
                        int Film);

        @Query("SELECT s FROM Showtime s WHERE s.startShow BETWEEN :startDate AND :endDate ORDER BY s.film.id, s.startShow")
        List<Showtime> findShowtimesInNext7Days(@Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

}
