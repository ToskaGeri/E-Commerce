package com.ecommerce.Controller.AuthController;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Dto.LoginDto;
import com.ecommerce.Dto.RegisterDto;
import com.ecommerce.Models.UserEntity;
import com.ecommerce.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ApiResponse<UserEntity> register(@RequestBody RegisterDto registerDto){
        return new ApiResponse<>(authService.register(registerDto), String.valueOf(HttpStatus.CREATED));
    }

    @PostMapping("login")
    public ApiResponse<LoginDto> login(@RequestBody RegisterDto registerDto){
        return new ApiResponse<>(
                authService.login(registerDto.userName(),registerDto.password()),
                String.valueOf(HttpStatus.OK));
    }
}
