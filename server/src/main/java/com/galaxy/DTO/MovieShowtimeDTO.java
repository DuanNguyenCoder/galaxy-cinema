package com.galaxy.DTO;

import lombok.Data;
import java.util.List;

@Data
public class MovieShowtimeDTO {
    private int filmID;
    private String name;
    private String image;
    private List<ShowtimeDTO> showTimes;
}
