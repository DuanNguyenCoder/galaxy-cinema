package com.galaxy.payoads;

import com.galaxy.enums.ResponseTitle;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private ResponseTitle title;
    private String message;
    private int status;
    private T data;

    public ApiResponse(ResponseTitle title, String message, int status, T data) {
        this.title = title;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(ResponseTitle title, String message, int status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }
}
