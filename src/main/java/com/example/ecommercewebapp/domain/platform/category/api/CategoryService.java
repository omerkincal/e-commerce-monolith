package com.example.ecommercewebapp.domain.platform.category.api;


import com.example.ecommercewebapp.domain.platform.category.impl.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto getCategory(String categoryId);
    CategoryDto updateCategory(CategoryDto categoryDto, String id);
    List<CategoryDto> getAllCategory();
    void delete(String id);
    Category getCategoryEntity(String id);
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
