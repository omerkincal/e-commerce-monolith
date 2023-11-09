package com.example.ecommercewebapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopAdminDto {
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;
}
