package com.galaxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.OrderFilm;

public interface OrderFilmRepo extends JpaRepository<OrderFilm, Integer> {

	List<OrderFilm> findByMovieIdAndInvoiceStatus(int id, String status);
}
