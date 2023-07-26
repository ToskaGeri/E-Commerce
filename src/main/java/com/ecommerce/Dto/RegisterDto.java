package com.ecommerce.Dto;

public record RegisterDto(String userName,
                          String password,
                          String address,
                          String mobile_nr,
                          String email) {
}
