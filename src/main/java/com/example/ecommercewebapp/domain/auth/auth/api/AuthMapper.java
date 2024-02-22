package com.example.ecommercewebapp.domain.auth.auth.api;


import com.example.ecommercewebapp.domain.auth.auth.web.*;
import com.example.ecommercewebapp.domain.auth.user.api.UserType;

public class AuthMapper {

    private AuthMapper() {
    }

    public static LoginDto toDto(LoginRequest loginRequest, UserType userType){
        return new LoginDto(
                loginRequest.username(),
                loginRequest.password(),
                userType);
    }

    public static SignUpDto toDto(SignUpRequest signUpRequest, UserType userType){
        return new SignUpDto(
                signUpRequest.password(),
                signUpRequest.email(),
                signUpRequest.name(),
                signUpRequest.surname(),
                signUpRequest.phoneNumber(),
                userType,
                signUpRequest.extensionNumber()
                );
    }

    public static ChangePasswordDto toDto(ChangePasswordRequest changePasswordRequest, UserType userType){
        return new ChangePasswordDto(
                changePasswordRequest.username(),
                userType
        );
    }

    public static PasswordResetDto toDto(PasswordResetRequest passwordResetRequest, UserType userType){
        return new PasswordResetDto(
                passwordResetRequest.newPassword(),
                passwordResetRequest.changePasswordCode(),
                userType);
    }



    public static LoginResponse toResponse(TokenDto tokenDto){
        return new LoginResponse(tokenDto.token());
    }
    public static SignUpResponse toResponseSignUp(TokenDto tokenDto){
        return new SignUpResponse(tokenDto.token());
    }


    public static ChangePasswordResponse toResponseChangePassword(TokenDto tokenDto) {
        return new ChangePasswordResponse(tokenDto.token());
    }
}
