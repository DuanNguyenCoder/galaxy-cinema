package com.galaxy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "combodoan")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaCombo")
    private int id;

    @Column(name = "TenCombo")
    private String name;

    @Column(name = "GiaCombo")
    private double price;

    @Column(name = "MoTa")
    private String describe;

}
