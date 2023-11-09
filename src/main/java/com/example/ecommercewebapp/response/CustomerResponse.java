package com.example.ecommercewebapp.response;

import com.example.ecommercewebapp.dto.BasketDto;
import com.example.ecommercewebapp.entity.Basket;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerResponse {
    private final int customerId;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final String address;
    private final String password;
    private final String message;
    private final int code;
    private List<BasketDto> basketList;
}
