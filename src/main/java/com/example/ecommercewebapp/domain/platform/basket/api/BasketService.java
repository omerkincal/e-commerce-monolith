package com.example.ecommercewebapp.domain.platform.basket.api;

import com.example.ecommercewebapp.domain.platform.basket.api.BasketDto;

public interface BasketService {
    BasketDto addProductToBasket(BasketDto basketDto);
    BasketDto getBasketById(String customerId);
    String removeProductFromBasket(String basketItemId);
}


