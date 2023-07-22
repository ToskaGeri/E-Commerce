package com.ecommerce.Exceptions;

import com.ecommerce.Errors.CustomError;

public class OrderDoesNotExist extends CustomError {

    public OrderDoesNotExist(String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }

}
