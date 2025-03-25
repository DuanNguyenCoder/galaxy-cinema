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

	public Page<Customer> getCustomers(Pageable pageable, String search) {
		if (search != null && !search.isEmpty()) {
			return cusRepo.findByNameContainingOrEmailContainingOrPhoneContaining(
					search, search, search, pageable);
		}
		return cusRepo.findAll(pageable);
	}

	public Customer updateCustomer(int id, Customer customerDetails) {
		Customer customer = getCustomerById(id);

		customer.setName(customerDetails.getName());
		customer.setPhone(customerDetails.getPhone());

		// Kiểm tra nếu email mới khác email cũ và đã tồn tại
		if (!customer.getEmail().equals(customerDetails.getEmail()) &&
				cusRepo.findByEmail(customerDetails.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email đã được sử dụng");
		}
		customer.setEmail(customerDetails.getEmail());

		// Cập nhật mật khẩu nếu có
		if (customerDetails.getPasswords() != null && !customerDetails.getPasswords().isEmpty()) {
			customer.setPasswords(passwordEncoder.encode(customerDetails.getPasswords()));
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
