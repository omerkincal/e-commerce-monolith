package com.example.ecommercewebapp.domain.auth.auth.api;


import com.example.ecommercewebapp.domain.auth.user.api.UserType;

public record SignUpDto(
        String password,
        String email,
        String name,
        String surname,
        String phoneNumber,
        UserType userType,
        String extensionNumber
) {

}
