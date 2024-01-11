package com.example.ecommercewebapp.domain.auth.user.impl;

public enum UserType {
    SUPER_ADMIN("SUPER_ADMIN"),
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
