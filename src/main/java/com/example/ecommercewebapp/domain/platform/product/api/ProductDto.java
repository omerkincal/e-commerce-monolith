package com.example.ecommercewebapp.domain.platform.product.api;

import lombok.*;

@Data
@Builder
public class ProductDto {
    private  int productId;
    private  String name;
    private  int quantity;
    private  double price;
    private  int categoryId;

    public ProductDto() {
    }

    public ProductDto(int productId, String name, int quantity, double price, int categoryId) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    public ProductDto(int productId) {
        this.productId = productId;
    }
}
