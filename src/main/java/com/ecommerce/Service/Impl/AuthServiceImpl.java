package com.ecommerce.Service.Impl;

import com.ecommerce.Dto.LoginDto;
import com.ecommerce.Dto.RegisterDto;
import com.ecommerce.Models.Cart;
import com.ecommerce.Models.Role;
import com.ecommerce.Models.UserEntity;
import com.ecommerce.Repository.CartRepository;
import com.ecommerce.Repository.RoleRepository;
import com.ecommerce.Repository.UserRepository;
import com.ecommerce.Service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthServiceImpl(UserRepository userRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public UserEntity register(RegisterDto registerDto) {

        UserEntity newUser = new UserEntity();
        newUser.setAddress(registerDto.address());
        newUser.setEmail(registerDto.email());
        newUser.setMobileNumber(registerDto.mobile_nr());
        newUser.setUserName(registerDto.userName());
        newUser.setPassword(passwordEncoder.encode(registerDto.password()));

        Role role = roleRepository.findByAuthority("USER").get();

        List<Role> roles = new ArrayList<>();
        roles.add(role);
        newUser.setRoles(roles);

        Cart cart = new Cart();
        cart.setUser(newUser);
        newUser.setCart(cart);

        cartRepository.saveAndFlush(cart);
        userRepository.saveAndFlush(newUser);
        return newUser;
    }

    @Override
    public LoginDto login(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = tokenService.generateToken(authentication);
        return new LoginDto(userRepository.findByUserName(username).get(), token);

    }
}
