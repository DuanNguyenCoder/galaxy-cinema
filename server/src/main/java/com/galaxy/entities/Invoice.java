package com.galaxy.entities;

import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "hoadon")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaHoaDon")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaKhachHang", nullable = false)
	private Customer Customer;

	@Column(name = "TongTien")
	private float total;

	@Column(name = "NgayXuatHoaDon")
	private LocalDateTime dayCreateBill;

	@Column(name = "TrangThai", nullable = false)
	private String status;

	@OneToMany(mappedBy = "invoice")
	@JsonIgnore
	List<OrderFilm> orderFilms;
}
