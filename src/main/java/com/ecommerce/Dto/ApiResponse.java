package com.ecommerce.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {

    private boolean success;
    private T responseBody;
    private String errorMessage;
    private int errorCode;
    private String successMessage;


    public ApiResponse(T responseBody, String successMessage) {
        this.success = true;
        this.responseBody = responseBody;
        this.errorMessage = "";
        this.errorCode = 0;
        this.successMessage = successMessage;
    }

    public ApiResponse(String errorMessage, int errorCode) {
        this.success = false;
        this.responseBody = null;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.successMessage = "";
    }
}