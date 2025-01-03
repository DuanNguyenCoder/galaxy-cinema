package com.galaxy.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "doan")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDoAn")
    private int id;

    @Column(name = "Ten")
    private String name;

    @Column(name = "Gia")
    private double price;

    @Column(name = "LoaiDoAn")
    private String type;

    @Column(name = "HangTon")
    private int stock;

    @Column(name = "TrangThaiCoSan")
    private int avaiable;

    @Column(name = "NgayTao")
    private Date createDay;

    @Column(name = "NgayCapNhat")
    private Date updateDay;

    @Column(name = "HinhAnh")
    private String image;

}
