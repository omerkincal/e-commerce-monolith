package com.example.ecommercewebapp.domain.auth.auth.web;

public record SignUpRequest(
        String username,
        String password,
        String name,
        String surname,
        String email,
        String phoneNumber,
        String extensionNumber
) {
}
