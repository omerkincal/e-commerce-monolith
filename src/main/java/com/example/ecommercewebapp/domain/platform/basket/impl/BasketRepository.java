package com.example.ecommercewebapp.domain.platform.basket.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, String> {
    Basket findByUserIdAndStatusEquals(String userId, int status);
}
