package com.ecommerce.Service.Impl;

import com.ecommerce.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        if(!username.equals("gege")) throw new UsernameNotFoundException(USERNAME_NOT_FOUND_ERROR_MESSAGE);
//
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Role(2L,"ADMIN"));
//
//        return new UserEntity("gege", passwordEncoder.encode("gege"), roles );
        return userRepository.findByUserName(username).get();
    }
}
