package com.galaxy.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShowtimeRequestDTO {
	private int filmID;
	private LocalDateTime startShow;
	private String screen;
}
