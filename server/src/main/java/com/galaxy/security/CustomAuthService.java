package com.galaxy.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.galaxy.entities.Customer;
import com.galaxy.repositories.CustomerRepo;
import java.util.Collections;

@Service
public class CustomAuthService implements UserDetailsService {
    @Autowired
    private CustomerRepo cusRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> cus = cusRepo.findByEmail(email);
        if (cus.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            Customer cutomer = cus.get();
            return new org.springframework.security.core.userdetails.User(
                    cutomer.getEmail(),
                    cutomer.getPasswords(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }
    }
}
