package com.galaxy.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "khachhang")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaKhachHang")
	private int id;
	@Column(name = "HoTen")
	private String name;
	@Column(name = "NgayDangKy")
	private String createDate;
	@Column(name = "SoDienThoai")
	private String Phone;
	@Column(name = "Email")
	private String email;
	@Column(name = "MatKhau")
	@JsonIgnore
	private String passwords;
	@Column(name = "Diem")
	@JsonIgnore
	private Integer point;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Rating> rating;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<OrderFilm> orderFilms;

}
