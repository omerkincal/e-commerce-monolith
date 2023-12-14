package com.example.ecommercewebapp.domain.platform.basket.impl;

import com.example.ecommercewebapp.domain.platform.basket.api.BasketDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductService;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerService;

import java.util.List;

public class BasketMapper {

    private BasketMapper(){

    }
    public static BasketDto toDto(Basket basket , BasketProductService basketProductService, CustomerService customerService) {
        List<BasketProductDto> products = basketProductService.getAllByBasketId(basket.getId());
        CustomerDto customer = customerService.getById(basket.getCustomerId());

        return BasketDto.builder()
                .id(basket.getId())
                .created(basket.getCreated())
                .modified(basket.getModified())
                .totalAmount(basket.getTotalAmount())
                .customer(customer)
                .products(products)
                .status(basket.getStatus())
                .build();
    }

    public static Basket toEntity(Basket basket,BasketDto basketDto) {
        basket.setStatus(basketDto.getStatus());
        basket.setCustomerId(basketDto.getCustomer().getId());
        return basket;
    }
}
