package com.example.ecommercewebapp.domain.platform.category.impl;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import com.example.ecommercewebapp.domain.platform.category.api.CategoryService;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import com.example.ecommercewebapp.library.utils.PageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return CategoryMapper.toDto(repository.save(CategoryMapper.toEntity(new Category(), categoryDto)));
    }

    @Override
    public CategoryDto getById(String categoryId) {
        return CategoryMapper.toDto(repository.findById(categoryId).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Category.class.getSimpleName(), categoryId)));
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String id) {
        Category category = repository.findById(id).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Category.class.getSimpleName(), id));
        return CategoryMapper.toDto(repository.save(setCategory(category, categoryDto)));
    }

    @Override
    public Page<CategoryDto> getAllCategory(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), category -> CategoryMapper.toDto(category));
    }

    @Override
    @Transactional
    public void delete(String id) {
        Category category = repository.findById(id).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Category.class.getSimpleName(), id));
        repository.delete(category);
    }


    private Category setCategory(Category category, CategoryDto categoryDto) {
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;

    }
}
