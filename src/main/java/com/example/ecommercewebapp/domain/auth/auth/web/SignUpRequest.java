package com.example.ecommercewebapp.domain.auth.auth.web;

public record SignUpRequest(
        String password,
        String email,
        String name,
        String surname,
        String phoneNumber,
        String extensionNumber
) {

}
