package com.galaxy.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "vexemphim")
public class OrderFilm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaVe")
	private int id;
	@Column(name = "SoGhe")
	private String seat;
	@Column(name = "GiaVe")
	private float ticketPrice;
	@Column(name = "NgayMua")
	private Date day;
	@Column(name = "LoaiVe")
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaKhachHang")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaSuatChieu")
	private Showtime movie;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaHoaDon")
	private Invoice invoice;

}
