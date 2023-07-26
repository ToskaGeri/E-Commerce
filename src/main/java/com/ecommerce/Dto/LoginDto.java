package com.ecommerce.Dto;

import com.ecommerce.Models.UserEntity;

public record LoginDto(UserEntity user, String jwt) {
}
