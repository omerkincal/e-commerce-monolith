package com.example.ecommercewebapp.dto;

import com.example.ecommercewebapp.entity.Basket;
import lombok.*;

import java.util.List;

@Data
@Builder
public class CustomerDto {
    private  int customerId;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;
    private final List<BasketDto> basketList;
}
