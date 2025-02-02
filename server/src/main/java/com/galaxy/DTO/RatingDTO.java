package com.galaxy.DTO;

import java.util.List;

import com.galaxy.entities.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

	private String filmName;
	private double averageRating;
	private List<Rating> ratings;

}
