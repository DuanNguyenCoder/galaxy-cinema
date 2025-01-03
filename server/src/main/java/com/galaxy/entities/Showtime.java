package com.galaxy.entities;

import java.time.LocalDateTime;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "suatchieu")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Showtime {

	@Id
	@Column(name = "MaSuatChieu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "MaPhim", nullable = false)
	private Film film;

	@Column(name = "ThoiGianBatDau")
	private LocalDateTime startShow;

	@Column(name = "PhongChieu")
	private String screen;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<OrderFilm> orderFilms;

}
