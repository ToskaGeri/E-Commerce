package com.ecommerce.Exceptions;

import com.ecommerce.Errors.CustomError;

public class ProductNotFoundException extends CustomError {

    public ProductNotFoundException(String message,int errorCode){
        super(message,errorCode);
    }
}
