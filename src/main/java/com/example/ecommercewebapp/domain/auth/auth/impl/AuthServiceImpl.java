package com.example.ecommercewebapp.domain.auth.auth.impl;

import com.example.ecommercewebapp.domain.auth.auth.api.AuthService;
import com.example.ecommercewebapp.domain.auth.auth.api.LoginDto;
import com.example.ecommercewebapp.domain.auth.auth.api.SignUpDto;
import com.example.ecommercewebapp.domain.auth.auth.api.TokenDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.domain.auth.user.impl.User;
import com.example.ecommercewebapp.domain.auth.user.impl.UserMapper;
import com.example.ecommercewebapp.library.security.CustomUserDetails;
import com.example.ecommercewebapp.library.security.JwtService;
import com.example.ecommercewebapp.library.security.JwtUtils;
import com.example.ecommercewebapp.library.security.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;


    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public TokenDto login(LoginDto loginDto) {

        //CustomUserDetails user = userDetailsService.loadUserByUsername(loginDto.username());

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.username(), loginDto.password()));


        String token = JwtUtils.generateToken(((UserDetails)(authentication.getPrincipal())).getUsername());

        return new TokenDto(token);


/*
        if (authentication.isAuthenticated()) {
            String token = jwtUtils.generateToken(user.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new TokenDto(token);
        }
        throw new UsernameNotFoundException("invalid username {} " + loginDto.username());
*/

    }

    @Override
    public TokenDto signUp(SignUpDto signUpDto) {
        UserDto user = userService.createUser(UserMapper.toDto(signUpDto));
        User savedUser = UserMapper.toEntity(new User() , user , passwordEncoder);
        UserDetails userDetails = new CustomUserDetails(savedUser);
        String token = JwtUtils.generateToken(userDetails.getUsername());
        return new TokenDto(token);
    }



}
