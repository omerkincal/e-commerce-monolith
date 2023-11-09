package com.example.ecommercewebapp.repository;

import com.example.ecommercewebapp.entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem,Integer> {
    BasketItem findBasketItemByBasket_BasketIdAndProduct_ProductId(int basketId, int productId);
    void deleteBasketItemByProduct_ProductId(int id);
}
