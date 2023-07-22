package com.ecommerce.Exceptions;

import com.ecommerce.Errors.CustomError;

public class UserNotFoundException extends CustomError {

    public UserNotFoundException(String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}
