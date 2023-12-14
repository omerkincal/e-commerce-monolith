package com.example.ecommercewebapp.domain.platform.product.api;

import lombok.*;

@Data
@Builder
public class ProductDto {
    private  String id;
    private  String name;
    private  int quantity;
    private  double price;
    private  int categoryId;

    public ProductDto() {
    }

    public ProductDto(String productId, String name, int quantity, double price, int categoryId) {
        this.id = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    public ProductDto(String productId) {
        this.id = productId;
    }
}
