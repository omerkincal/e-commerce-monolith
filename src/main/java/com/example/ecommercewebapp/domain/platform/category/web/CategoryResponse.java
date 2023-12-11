package com.example.ecommercewebapp.domain.platform.category.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private final int categoryId;
    private final String categoryName;
    private final String message;
    private final int code;
    private final int shopId;
}
