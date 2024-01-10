package com.example.ecommercewebapp.domain.platform.category.api;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getById(String categoryId);
    CategoryDto update(CategoryDto categoryDto, String id);
    Page<CategoryDto> getAllCategory(Pageable pageable);
    void delete(String id);
}
