package com.example.ecommercewebapp.domain.auth.auth.api;

import com.example.ecommercewebapp.domain.auth.user.impl.UserType;

public record SignUpDto(
        String username,
        String password,
        String email,
        String name,
        String surname,
        String phoneNumber,
        String extensionNumber,
        UserType userType

) {
}
