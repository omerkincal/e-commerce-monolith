package com.example.ecommercewebapp.repository;

import com.example.ecommercewebapp.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Integer> {
    Basket findBasketByCustomer_CustomerIdAndStatusEquals(int customerId, int status);
    Basket findBasketByBasketId(int basketId);
}
