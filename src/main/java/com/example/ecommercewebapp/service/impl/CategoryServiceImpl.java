package com.example.ecommercewebapp.service.impl;

import com.example.ecommercewebapp.dto.CategoryDto;
import com.example.ecommercewebapp.entity.Category;
import com.example.ecommercewebapp.repository.CategoryRepository;
import com.example.ecommercewebapp.service.CategoryService;
import com.example.ecommercewebapp.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final ShopService shopService;

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
        category.setShop(shopService.getShopEntity(String.valueOf(categoryDto.getShopId())));
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
                .shopId(category.getShop().getShopId())
                .build();
    }
    @Override
    public Category toEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setShop(shopService.getShopEntity(String.valueOf(categoryDto.getShopId())));
        return category;
    }
}
