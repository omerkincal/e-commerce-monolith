package com.example.ecommercewebapp.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopAdminResponse {
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;
    private final String message;
    private final int code;
}
