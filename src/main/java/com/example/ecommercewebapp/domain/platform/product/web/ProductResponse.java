package com.example.ecommercewebapp.domain.platform.product.web;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductResponse {
    private String id;
    private Date created;
    private Date modified;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private CategoryDto category;
}
