package com.example.ecommercewebapp.domain.platform.product.web;

import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String name;
    private int quantity;
    private double price;
    private int categoryId;

    public ProductDto toDto(){
        return ProductDto.builder()
                .name(this.name)
                .quantity(this.quantity)
                .price(this.price)
                .categoryId(this.categoryId)
                .build();
    }
}
