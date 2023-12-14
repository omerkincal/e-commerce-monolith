package com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct;

import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketProductServiceImpl implements BasketProductService {

    private final BasketProductRepository repository;



}
