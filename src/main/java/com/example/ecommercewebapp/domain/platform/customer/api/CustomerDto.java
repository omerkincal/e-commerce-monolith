package com.example.ecommercewebapp.domain.platform.customer.api;

import com.example.ecommercewebapp.domain.platform.basket.api.BasketDto;
import lombok.*;

import java.util.List;

@Data
@Builder
public class CustomerDto {
    private String id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;
    private final List<BasketDto> basketList;
}
