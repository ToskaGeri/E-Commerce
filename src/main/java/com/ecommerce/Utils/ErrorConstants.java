package com.ecommerce.Utils;

public class ErrorConstants {

    private ErrorConstants() {
        throw new IllegalStateException("Must not instantiate utility class");
    }

    public static final String PRODUCT_NOT_FOUND_ERROR_MESSAGE = "Product not found!";

    public static final int PRODUCT_NOT_FOUND_ERROR_CODE = 1;
}
