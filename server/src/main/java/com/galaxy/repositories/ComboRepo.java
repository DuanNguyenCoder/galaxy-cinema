package com.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.Combo;

public interface ComboRepo extends JpaRepository<Combo, Integer> {

    public Combo findByNameContaining(String name);

}
