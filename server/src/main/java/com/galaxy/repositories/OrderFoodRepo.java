package com.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.OrderFood;

public interface OrderFoodRepo extends JpaRepository<OrderFood, Integer> {

}
