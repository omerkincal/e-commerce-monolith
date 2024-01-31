package com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketProductRepository extends JpaRepository<BasketProduct,String> {
    BasketProduct findAllByBasketIdAndProductId(String basketId, String productId);

    List<BasketProduct> findAllByBasketId(String id);
}
