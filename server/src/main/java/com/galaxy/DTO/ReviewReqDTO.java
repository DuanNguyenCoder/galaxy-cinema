package com.galaxy.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class ReviewReqDTO {
    private int filmID;
    private String comment;
    private int rate;
    private Date rateDate;

}
