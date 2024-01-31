package com.example.ecommercewebapp.domain.platform.basket.api.basketproduct;

import com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct.BasketProduct;

import java.util.List;

public interface BasketProductService {
    BasketProductDto save(BasketProduct basketProduct);
    BasketProductDto getById(String id);
    BasketProductDto update(String id, BasketProduct basketProduct);
    void delete(String id);
    List<BasketProductDto> getAllByBasketId(String id);
    BasketProduct findBasketProductByBasketIdAndProductId(String basketProductId, String productId);

}
