package com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct;

import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketProductServiceImpl implements BasketProductService {

    private final BasketProductRepository repository;


    @Override
    public BasketProductDto save(BasketProductDto basketProduct) {
        return null;
    }

    @Override
    public BasketProductDto getById(String id) {
        return null;
    }

    @Override
    public BasketProductDto update(String id, BasketProductDto basketProduct) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<BasketProductDto> getAllByBasketId(String id) {
        return null;
    }

    @Override
    public BasketProductDto findBasketProductByBasketIdAndProductId(String basketProductId, String productId) {
        return null;
    }
}
