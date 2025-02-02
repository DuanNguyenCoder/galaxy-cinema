package com.galaxy.services;

import java.util.ArrayList;
import java.util.Collections;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.DTO.AlgorithmDataResDTO;
import com.galaxy.DTO.RatingDTO;
import com.galaxy.DTO.ReviewReqDTO;
import com.galaxy.entities.Customer;
import com.galaxy.entities.Film;
import com.galaxy.entities.Rating;

import com.galaxy.repositories.CustomerRepo;
import com.galaxy.repositories.FilmRepo;
import com.galaxy.repositories.RatingRepo;

@Service
public class RatingService {

    @Autowired
    private CustomerRepo cusRepo;

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private FilmRepo MovieRe;

    @Autowired
    private FilmRepo filmRepo;

    public RatingDTO getFilmRatingsWithAverage(int filmId) {
        List<Rating> ratings = ratingRepo.findByFilmId(filmId);
        Film film = filmRepo.findById(filmId).orElseThrow();

        double averageRating = ratings.stream()
                .mapToInt(Rating::getRate)
                .average()
                .orElse(0.0);

        return new RatingDTO(
                film.getName(),
                averageRating,
                ratings);
    }

    public boolean insertUserReview(int cusId, ReviewReqDTO review) {
        try {
            Film f = filmRepo.findById(review.getFilmID()).get(); // get film by id
            Customer c = cusRepo.findById(cusId).get();
            System.out.println(f.getName());
            System.out.println(c.getName());
            Rating rating = new Rating();
            rating.setFilm(f);
            rating.setCustomer(c);
            rating.setRate(review.getRate());
            rating.setRateDate(review.getRateDate());
            rating.setComment(review.getComment());
            ratingRepo.save(rating);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


}
