package com.example.ecommercewebapp.request;

import com.example.ecommercewebapp.dto.CustomerDto;
import com.example.ecommercewebapp.dto.ShopAdminDto;
import com.example.ecommercewebapp.entity.Shop;
import com.example.ecommercewebapp.entity.ShopAdmin;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopAdminRequest {
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;


    public ShopAdminDto toDto(){
        return ShopAdminDto.builder()
                .address(this.address)
                .surname(this.surname)
                .phone(this.phone)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
