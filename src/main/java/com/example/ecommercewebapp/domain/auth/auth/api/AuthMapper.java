package com.example.ecommercewebapp.domain.auth.auth.api;

import com.example.ecommercewebapp.domain.auth.auth.web.LoginRequest;
import com.example.ecommercewebapp.domain.auth.auth.web.LoginResponse;
import com.example.ecommercewebapp.domain.auth.user.impl.UserType;

public class AuthMapper {

    public AuthMapper(){}

    public static LoginDto toDto(LoginRequest loginRequest, UserType userType){
        return new LoginDto(
                loginRequest.username(),
                loginRequest.password(),
                userType);
    }

    public static LoginResponse toResponse(TokenDto tokenDto){
        return new LoginResponse(tokenDto.token());
    }
}
