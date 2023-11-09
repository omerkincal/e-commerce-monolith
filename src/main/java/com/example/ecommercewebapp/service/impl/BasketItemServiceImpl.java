package com.example.ecommercewebapp.service.impl;

import com.example.ecommercewebapp.dto.BasketItemDto;
import com.example.ecommercewebapp.entity.BasketItem;
import com.example.ecommercewebapp.repository.BasketItemRepository;
import com.example.ecommercewebapp.service.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemRepository repository;

    private final ProductServiceImpl productService;

    public BasketItem findBasketItemByBasketIdAndProductId(int basketId, int productId){
        return repository.findBasketItemByBasket_BasketIdAndProduct_ProductId(basketId,productId);
    }

    public BasketItem save(BasketItem basketItem){
        return repository.save(basketItem);
    }

    public void delete(int basketItemId){
        BasketItem basketItem = repository.findById(basketItemId).get();
        repository.delete(basketItem);
    }


    public BasketItemDto toDto(BasketItem basketItem) {
        return BasketItemDto.builder()
                .basketItemId(basketItem.getBasketItemId())
                .basketItemAmount(basketItem.getBasketItemAmount())
                .count(basketItem.getCount())
                .product(productService.toDto(basketItem.getProduct()))
                .build();
    }
}
