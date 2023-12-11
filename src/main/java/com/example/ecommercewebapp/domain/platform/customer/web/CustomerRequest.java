package com.example.ecommercewebapp.domain.platform.customer.web;

import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;


    public CustomerDto toDto(){
        return CustomerDto.builder()
                .address(this.address)
                .surname(this.surname)
                .phone(this.phone)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
