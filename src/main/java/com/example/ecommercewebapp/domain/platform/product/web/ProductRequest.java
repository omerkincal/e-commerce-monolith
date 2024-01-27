package com.example.ecommercewebapp.domain.platform.product.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private String categoryId;
}
