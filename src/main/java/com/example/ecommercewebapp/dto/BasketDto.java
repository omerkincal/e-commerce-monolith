package com.example.ecommercewebapp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketDto {
    private  int basketId;
    private  double totalAmount;
    private  CustomerDto customer;
    private List<BasketItemDto> basketItemList;
}
