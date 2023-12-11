package com.example.ecommercewebapp.domain.platform.basket.api;

import com.example.ecommercewebapp.domain.platform.basket.api.basketitem.BasketItemDto;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketDto {
    private  int basketId;
    private  double totalAmount;
    private CustomerDto customer;
    private List<BasketItemDto> basketItemList;
}
