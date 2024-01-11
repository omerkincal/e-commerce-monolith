package com.example.ecommercewebapp.domain.auth.auth.impl;

import com.example.ecommercewebapp.domain.auth.auth.api.AuthService;
import com.example.ecommercewebapp.domain.auth.auth.api.LoginDto;
import com.example.ecommercewebapp.domain.auth.auth.api.SignUpDto;
import com.example.ecommercewebapp.domain.auth.auth.api.TokenDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.domain.auth.user.impl.User;
import com.example.ecommercewebapp.domain.auth.user.impl.UserMapper;
import com.example.ecommercewebapp.library.security.JwtService;
import com.example.ecommercewebapp.library.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private JwtService jwtService;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;


    public AuthServiceImpl(UserService userService, JwtService jwtService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public TokenDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails user = userDetailsService.loadUserByUsername(loginDto.username());
        String token = jwtService.generateToken(user.getUsername());

        return new TokenDto(token);
    }

    @Override
    public TokenDto signUp(SignUpDto signUpDto) {
        UserDto user = userService.createUser(UserMapper.toDto(signUpDto));
        String token = jwtService.generateToken(user.getUsername());
        return new TokenDto(token);
    }
}
