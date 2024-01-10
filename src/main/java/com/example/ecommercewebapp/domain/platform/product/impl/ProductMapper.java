package com.example.ecommercewebapp.domain.platform.product.impl;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import com.example.ecommercewebapp.domain.platform.category.api.CategoryService;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;

public class ProductMapper {
    public ProductMapper(){
    }

    public static ProductDto toDto(Product product, CategoryService categoryService){
        return ProductDto.builder()
                .id(product.getId())
                .created(product.getCreated())
                .modified(product.getModified())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .category(categoryService.getById(product.getCategoryId()))
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
