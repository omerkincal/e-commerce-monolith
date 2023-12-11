package com.example.ecommercewebapp.domain.platform.basket.impl.basketitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem,Integer> {
    BasketItem findBasketItemByBasket_BasketIdAndProduct_ProductId(int basketId, int productId);
    void deleteBasketItemByProduct_ProductId(int id);
}
