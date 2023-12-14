package com.example.ecommercewebapp.domain.platform.basket.api.basketproduct;

import java.util.List;

public interface BasketProductService {
    BasketProductDto save(BasketProductDto basketProduct);
    BasketProductDto getById(String id);
    BasketProductDto update(String id, BasketProductDto basketProduct);
    void delete(String id);
    List<BasketProductDto> getAllByBasketId(String id);
    BasketProductDto findBasketProductByBasketIdAndProductId(String basketProductId, String productId);

}
