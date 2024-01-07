package com.example.ecommercewebapp.domain.platform.product.api;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import com.example.ecommercewebapp.domain.platform.product.web.ProductRequest;
import com.example.ecommercewebapp.domain.platform.product.web.ProductResponse;
import com.example.ecommercewebapp.library.utils.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductMapper {
    public ProductMapper(){

    }

    public static ProductDto toDto(ProductRequest request){
        return ProductDto.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .description(request.getDescription())
                .category(CategoryDto.builder().id(request.getCategoryId()).build())
                .build();
    }


    public static ProductResponse toResponse(ProductDto product){
        return ProductResponse.builder()
                .id(product.getId())
                .created(product.getCreated())
                .modified(product.getModified())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .category(product.getCategory())
                .build();
    }

    public static Page<ProductResponse> toPageResponse(Page<ProductDto> productDtoList) {
        return PageUtil.pageToDto(productDtoList, ProductMapper::toResponse);
    }


    public static List<ProductResponse> toResponse(List<ProductDto> productDtos) {
        return productDtos.stream().map(ProductMapper::toResponse).toList();
    }
}
