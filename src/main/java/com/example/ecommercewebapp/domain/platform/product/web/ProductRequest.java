package com.example.ecommercewebapp.domain.platform.product.web;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductRequest {
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private String categoryId;
}
