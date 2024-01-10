package com.example.ecommercewebapp.domain.auth.auth.impl;

import com.example.ecommercewebapp.domain.auth.auth.api.AuthService;
import com.example.ecommercewebapp.domain.auth.auth.api.LoginDto;
import com.example.ecommercewebapp.domain.auth.auth.api.TokenDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.domain.auth.user.impl.User;
import com.example.ecommercewebapp.library.security.JwtService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private JwtService jwtService;
    private BCryptPasswordEncoder passwordEncoder;


    public AuthServiceImpl(UserService userService, JwtService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenDto login(LoginDto loginDto) {
        User user = userService.findByUsernameAndPasswordAndUserType(loginDto.username(), loginDto.password(), loginDto.userType());
        String token = jwtService.generateToken(user.getUsername());
        return new TokenDto(token);
    }
}
