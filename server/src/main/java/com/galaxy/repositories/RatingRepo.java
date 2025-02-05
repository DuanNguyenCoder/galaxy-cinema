package com.galaxy.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

	List<Rating> findByCustomerId(int cusID);

	List<Rating> findByFilmId(int film);

}