package com.example.ecommercewebapp.domain.platform.category.impl;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import com.example.ecommercewebapp.domain.platform.category.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;


    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = toEntity(categoryDto);
        category = repository.save(category);
        return toDto(category);
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = repository.findById(Integer.parseInt(categoryId)).get();
        return toDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String id) {
        Category category = getCategoryEntity(id);
        category.setCategoryName(categoryDto.getCategoryName());
        category = repository.save(category);
        return toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return repository.findAll()
                .stream()
                .map(category -> toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Category category = getCategoryEntity(id);
        repository.delete(category);
    }

    @Override
    public Category getCategoryEntity(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }

    @Override
    public CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();
    }
    @Override
    public Category toEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
