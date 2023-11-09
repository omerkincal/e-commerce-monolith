package com.example.ecommercewebapp.request;

import com.example.ecommercewebapp.dto.ProductDto;
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
