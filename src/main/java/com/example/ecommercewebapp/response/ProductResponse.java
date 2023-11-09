package com.example.ecommercewebapp.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private final int productId;
    private final String name;
    private final int quantity;
    private final double price;
    private final int categoryId;
}
