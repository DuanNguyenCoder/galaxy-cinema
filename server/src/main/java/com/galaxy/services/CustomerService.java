package com.galaxy.services;

import java.lang.reflect.Type;
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

	public Page<Customer> getCustomers(Pageable pageable, String search) {
		if (search != null && !search.isEmpty()) {
			return cusRepo.findByNameContainingOrEmailContainingOrPhoneContaining(
					search, search, search, pageable);
		}
		return cusRepo.findAll(pageable);
	}

	public Customer updateCustomer(int id, Map<String, String> customerDetails) {
		Customer customer = getCustomerById(id);

		customer.setName(customerDetails.get("name"));
		customer.setPhone(customerDetails.get("phone"));

		// Kiểm tra email
		String newEmail = customerDetails.get("email");
		if (!customer.getEmail().equals(newEmail) &&
				cusRepo.findByEmail(newEmail).isPresent()) {
			throw new IllegalArgumentException("Email đã được sử dụng");
		}
		customer.setEmail(newEmail);
		String newPassword = customerDetails.get("passwords");
		if (newPassword != null && !newPassword.isEmpty()) {
			customer.setPasswords(passwordEncoder.encode(newPassword));
		}

		return cusRepo.save(customer);
	}

	public void deleteCustomer(int id) {
		Customer customer = getCustomerById(id);
		cusRepo.delete(customer);
	}

	public Customer getCustomerById(int id) {
		return cusRepo.findById(id).get();
	}

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
