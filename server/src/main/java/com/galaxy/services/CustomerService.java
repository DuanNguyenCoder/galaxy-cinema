package com.galaxy.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.galaxy.entities.Customer;
import com.galaxy.repositories.CustomerRepo;
import com.galaxy.security.JwtUtil;

@Service
public class CustomerService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwt;

	@Autowired
	CustomerRepo cusRepo;




	public Map<String, Object> getProfile(String token) {
		return jwt.extractCustomerData(token);
	}

	public Optional<Customer> getLogin(String email) {
		return cusRepo.findByEmail(email);
	}

	public void createCustomer(Map<String, String> customer) {
		Customer customerEntity = new Customer();
		customerEntity.setName(customer.get("name"));
		customerEntity.setPhone(customer.get("phone"));
		customerEntity.setEmail(customer.get("email"));
		customerEntity.setPasswords(passwordEncoder.encode(customer.get("passwords")));
		cusRepo.save(customerEntity);
	}

}
