package com.galaxy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.repositories.OrderFilmRepo;
import com.galaxy.entities.OrderFilm;

@Service
public class OrderFilmService {

	@Autowired
	OrderFilmRepo orderFilmRepo;

	public void save(OrderFilm orderFilm) {
		orderFilmRepo.save(orderFilm);
	}

}
