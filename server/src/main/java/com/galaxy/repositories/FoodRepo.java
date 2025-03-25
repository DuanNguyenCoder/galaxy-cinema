package com.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.Food;

public interface FoodRepo extends JpaRepository<Food, Integer> {

}
