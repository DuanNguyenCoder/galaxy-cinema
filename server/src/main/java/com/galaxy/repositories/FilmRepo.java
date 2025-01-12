package com.galaxy.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.Film;

public interface FilmRepo extends JpaRepository<Film, Integer> {

	List<Film> findByActive(int active);

	Page<Film> findByName(String name, Pageable pageable);
}