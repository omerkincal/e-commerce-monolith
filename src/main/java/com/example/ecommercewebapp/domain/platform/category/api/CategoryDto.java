package com.example.ecommercewebapp.domain.platform.category.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private final int categoryId;
    private final String categoryName;
    private final int shopId;
}
