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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "danhgia")
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaDanhGia")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaPhim")
	@JsonIgnore
	private Film film;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaKhach")
	private Customer customer;

	@Column(name = "DiemDanhGia")
	private int rate;

	@Column(name = "NgayDanhGia")
	@Temporal(TemporalType.DATE)
	private Date rateDate;

	@Column(name = "BinhLuan")
	private String comment;

}
