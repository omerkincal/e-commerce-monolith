package com.example.ecommercewebapp.service;

import com.example.ecommercewebapp.dto.BasketDto;
import com.example.ecommercewebapp.dto.BasketItemDto;
import com.example.ecommercewebapp.entity.Basket;

import java.util.List;

public interface BasketService {
    BasketDto addProductToBasket(BasketDto basketDto);
    BasketDto getBasketById(String customerId);
    String removeProductFromBasket(String basketItemId);
}


