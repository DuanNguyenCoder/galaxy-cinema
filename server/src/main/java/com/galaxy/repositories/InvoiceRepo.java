package com.galaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.entities.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

}
