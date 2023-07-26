package com.ecommerce.Service;

import com.ecommerce.Dto.LoginDto;
import com.ecommerce.Dto.RegisterDto;
import com.ecommerce.Models.UserEntity;

public interface AuthService {

    public UserEntity register(RegisterDto registerDto);

    public LoginDto login(String username, String password);

}
