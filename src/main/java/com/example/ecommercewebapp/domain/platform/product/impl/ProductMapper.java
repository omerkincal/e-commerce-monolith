package com.example.ecommercewebapp.domain.platform.product.impl;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;

public class ProductMapper {
    public ProductMapper(){
    }

    public static ProductDto toDto(Product product){
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .category(CategoryDto.builder().id(product.getCategoryId()).build())
                .build();
    }

    public static Product toEntity(Product product, ProductDto dto){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        product.setCategoryId(dto.getCategory().getId());
        return product;
    }
}
