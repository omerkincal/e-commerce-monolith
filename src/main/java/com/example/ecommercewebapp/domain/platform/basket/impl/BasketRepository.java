package com.example.ecommercewebapp.domain.platform.basket.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,String> {
    Basket findBasketByCustomer_CustomerIdAndStatusEquals(int customerId, int status);
    Basket findBasketByBasketId(int basketId);
}
