package com.example.ecommercewebapp.domain.platform.basket.impl.basketitem;

import com.example.ecommercewebapp.domain.platform.basket.api.basketitem.BasketItemDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketitem.BasketItemService;
import com.example.ecommercewebapp.domain.platform.product.impl.ProductServiceImpl;
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
