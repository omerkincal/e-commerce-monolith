package com.example.ecommercewebapp.domain.platform.category.impl;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;

public class CategoryMapper {
    public CategoryMapper(){}

    public static CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .created(category.getCreated())
                .modified(category.getModified())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }


    public static Category toEntity(Category category, CategoryDto dto){
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}
