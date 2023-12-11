package com.example.ecommercewebapp.domain.platform.basket.api;

public interface BasketService {
    BasketDto addProductToBasket(BasketDto basketDto);
    BasketDto getBasketById(String customerId);
    String removeProductFromBasket(String basketItemId);
}


