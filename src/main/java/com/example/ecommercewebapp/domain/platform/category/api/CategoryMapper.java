package com.example.ecommercewebapp.domain.platform.category.api;

import com.example.ecommercewebapp.domain.platform.category.web.CategoryRequest;
import com.example.ecommercewebapp.domain.platform.category.web.CategoryResponse;
import com.example.ecommercewebapp.library.rest.PageResponse;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

public class CategoryMapper {
    public CategoryMapper(){


    }

    public static CategoryDto toDto(CategoryRequest request){
        return CategoryDto.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public static CategoryResponse toResponse(CategoryDto category){
        return CategoryResponse.builder()
                .id(category.getId())
                .created(category.getCreated())
                .modified(category.getModified())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Page<CategoryResponse> toPageResponse(Page<CategoryDto> allCategory) {
        return PageUtil.pageToDto(allCategory, CategoryMapper::toResponse);
    }

}
