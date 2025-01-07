package com.galaxy.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.security.authentication.*;

import com.galaxy.entities.Customer;
import com.galaxy.security.JwtUtil;
import com.galaxy.services.CustomerService;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.enums.ResponseTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("api/customer")

public class CustomerController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomerService customerService;


	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable int id) {
		Customer customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(new ApiResponse<>(
				ResponseTitle.SUCCESS,
				"Lấy thông tin khách hàng thành công",
				200,
				customer));
	}


	@GetMapping("/profile")
	public Map<String, Object> getLogin(@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new RuntimeException("Invalid Token");
		}
		String token = authHeader.substring(7);
		return customerService.getProfile(token);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> cus) {

		try {
			String email = cus.get("email");
			String passwords = cus.get("passwords");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, passwords));
			Customer c = customerService.getLogin(email).get();
			System.err.println(c.getEmail());
			Map<String, Object> claim = Map.of(
					"id", c.getId(),
					"name", c.getName(),
					"email", c.getEmail(),
					"point", Optional.ofNullable(c.getPoint()).orElse(0),
					"phone", Optional.ofNullable(c.getPhone()).orElse("N/A"));
			String token = jwtUtil.generateToken(email, claim);
			return ResponseEntity.ok(Map.of("token", token));

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("smg", "sai email hoặc mật khẩu"));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> createCustomer(@RequestBody Map<String, String> customer) {
		try {
			this.customerService.createCustomer(customer);
			return ResponseEntity.ok(new ApiResponse<>(
					ResponseTitle.SUCCESS,
					"Tạo khách hàng thành công",
					200,
					customer));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
					ResponseTitle.ERROR,
					"Lỗi khi tạo khách hàng",
					400,
					null));
		}
	}

}
