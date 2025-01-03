package com.galaxy.entities;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "phim")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaPhim")
	private int id;

	@Column(name = "TenPhim")
	private String name;

	@Column(name = "TheLoai")
	private String typefilm;

	@Column(name = "ThoiLuong")
	private int time;

	@Column(name = "HoatDong")
	private Integer active;

	@Column(name = "MoTa")
	private String description;

	@Column(name = "Link")
	private String link;

	@Column(name = "DaoDien")
	private String author;

	@Column(name = "DienVienChinh")
	private String actor;

	@Column(name = "DoTuoi")
	private Integer ageLimited;

	@Column(name = "HinhAnh", columnDefinition = "image")
	private String image;

	@Column(name = "NgayKhoiChieu")
	private Date dateStart;

	@Column(name = "QuocGia")
	private String country;

	@JsonIgnore
	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	private List<Showtime> showtime;

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rating> rating;

}
