package com.galaxy.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByEmail(String email);

	Page<Customer> findByNameContainingOrEmailContainingOrPhoneContaining(
			String name,
			String email,
			String phone,
			Pageable pageable);
}
