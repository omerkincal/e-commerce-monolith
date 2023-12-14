package com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct,String> {
    BasketProduct findBasketItemByBasket_BasketIdAndProduct_ProductId(int basketId, int productId);
}
