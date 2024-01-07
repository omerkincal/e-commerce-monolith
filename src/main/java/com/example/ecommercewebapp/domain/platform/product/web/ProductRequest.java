package com.example.ecommercewebapp.domain.platform.product.web;

import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private String categoryId;
}
