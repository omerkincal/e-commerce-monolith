package com.example.ecommercewebapp.domain.platform.customer.web;

import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerRequest {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String password;


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
