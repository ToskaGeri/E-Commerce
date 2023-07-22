package com.ecommerce.Utils;

public class ErrorConstants {

    private ErrorConstants() {
        throw new IllegalStateException("Must not instantiate utility class");
    }

    public static final String PRODUCT_NOT_FOUND_ERROR_MESSAGE = "Product not found!";

    public static final int PRODUCT_NOT_FOUND_ERROR_CODE = 1;

    public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found!";

    public static final int USER_NOT_FOUND_ERROR_CODE = 2;

    public static final String ORDER_DOES_NOT_EXIST_MESSAGE = "Order does not exist!";

    public static final int ORDER_DOES_NOT_EXIST_CODE = 3;
}
