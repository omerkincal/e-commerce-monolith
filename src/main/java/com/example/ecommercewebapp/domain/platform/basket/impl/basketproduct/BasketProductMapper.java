package com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct;

import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductService;

public class BasketProductMapper {

    public static BasketProductDto toDto(BasketProduct basketProduct, ProductService productService){
        ProductDto product = productService.getById(basketProduct.getProductId());

        return BasketProductDto.builder()
                .id(basketProduct.getId())
                .created(basketProduct.getCreated())
                .modified(basketProduct.getModified())
                .product(product)
                .basketId(basketProduct.getBasketId())
                .quantity(basketProduct.getQuantity())
                .basketProductAmount(basketProduct.getBasketProductAmount())
                .build();
    }


    public static BasketProduct toEntity(BasketProduct basketProduct, BasketProductDto dto){
        basketProduct.setBasketId(dto.getBasketId());
        basketProduct.setQuantity(dto.getQuantity());
        basketProduct.setProductId(dto.getProduct().getId());
        return basketProduct;
    }
}
