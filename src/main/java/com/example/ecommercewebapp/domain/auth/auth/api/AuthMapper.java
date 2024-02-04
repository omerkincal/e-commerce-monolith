package com.example.ecommercewebapp.domain.auth.auth.api;

import com.example.ecommercewebapp.domain.auth.auth.web.LoginRequest;
import com.example.ecommercewebapp.domain.auth.auth.web.LoginResponse;
import com.example.ecommercewebapp.domain.auth.auth.web.SignUpRequest;
import com.example.ecommercewebapp.domain.auth.auth.web.SignUpResponse;
import com.example.ecommercewebapp.domain.auth.user.impl.UserType;

public class AuthMapper {

    public AuthMapper() {
    }

    public static LoginDto toDto(LoginRequest loginRequest) {
        return new LoginDto(
                loginRequest.username(),
                loginRequest.password());
    }

    public static SignUpDto toDto(SignUpRequest signUpRequest, UserType userType) {
        return new SignUpDto(
                signUpRequest.username(),
                signUpRequest.password(),
                signUpRequest.email(),
                signUpRequest.name(),
                signUpRequest.surname(),
                signUpRequest.phoneNumber(),
                signUpRequest.extensionNumber(),
                userType);
    }

    public static SignUpResponse toSignUpResponse(TokenDto tokenDto) {
        return new SignUpResponse(tokenDto.token());
    }

    public static LoginResponse toResponse(TokenDto tokenDto) {
        return new LoginResponse(tokenDto.token());
    }
}
