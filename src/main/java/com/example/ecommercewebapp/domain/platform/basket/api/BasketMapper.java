package com.example.ecommercewebapp.domain.platform.basket.api;

import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductDto;
import com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct.BasketProduct;
import com.example.ecommercewebapp.domain.platform.basket.web.BasketRequest;
import com.example.ecommercewebapp.domain.platform.basket.web.BasketResponse;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class BasketMapper {

    private BasketMapper() {
    }

    public static BasketDto toDto(BasketRequest basketRequest) {

        List<BasketProductDto> products = new ArrayList<>();
        BasketProductDto product = new BasketProductDto();

        product.setProduct(ProductDto.builder().id(basketRequest.getProductId()).build());
        product.setQuantity((basketRequest.getQuantity()));
        products.add(product);


        return BasketDto.builder()
                .user(UserDto.builder()
                        .id(basketRequest.getUserId())
                        .build())
                .products(products)
                .build();
    }

    public static BasketResponse toResponse(BasketDto basketDto) {
        return BasketResponse.builder()
                .id(basketDto.getId())
                .created(basketDto.getCreated())
                .modified(basketDto.getModified())
                .user(basketDto.getUser())
                .totalAmount(basketDto.getTotalAmount())
                .status(basketDto.getStatus())
                .products(basketDto.getProducts())
                .build();
    }
}
