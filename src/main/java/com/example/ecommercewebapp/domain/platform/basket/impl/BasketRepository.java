package com.example.ecommercewebapp.domain.platform.basket.impl;

import com.example.ecommercewebapp.domain.platform.basket.impl.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Integer> {
    Basket findBasketByCustomer_CustomerIdAndStatusEquals(int customerId, int status);
    Basket findBasketByBasketId(int basketId);
}
